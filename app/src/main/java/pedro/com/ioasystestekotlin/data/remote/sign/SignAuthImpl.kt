package pedro.com.ioasystestekotlin.data.remote.sign

import android.app.Application
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import pedro.com.ioasystestekotlin.data.ext.headerMapper
import pedro.com.ioasystestekotlin.data.ext.putSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import retrofit2.HttpException
import java.io.IOException

class SignAuthImpl(val app: Application,
                   private val service: WebService) : SignAuth {

    override fun loginAccess(userApi: UserApi,
                             successLogin: (headers: HeaderApi) -> Unit,
                             errorLogin: (t: Throwable) -> Unit
    ): Job = launch {
        try {
            val response = service.authentication(userApi).await()

            if (!response.isSuccessful) {
                errorLogin(Exception(LoginFieldState.loginInvalid()))
                return@launch
            }

            if (response.body()?.success != true) {
                errorLogin(Exception(LoginFieldState.loginInvalid()))
                return@launch
            }
            val headers = response.headers()

            val headerApi = HeaderApi(
                    access_token = headers["access-token"] ?: "",
                    uid = headers["uid"] ?: "",
                    client = headers["client"] ?: ""
            )
            app.putSharedPreferences(
                    keyToAccess = "headers",
                    keys = headerApi.headerMapper()
            )

            successLogin(headerApi)


        } catch (httpException: HttpException) {
            errorLogin(Exception(httpException.message()))
        } catch (ioException: IOException) {
            errorLogin(Exception(ioException.message))
        }

    }
}
