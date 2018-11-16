package pedro.com.ioasystestekotlin.data.remote.sign

import android.app.Application
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import pedro.com.ioasystestekotlin.data.ext.putSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SignAuthImpl(val app: Application,
                   private val service: WebService) : SignAuth {

    override fun loginAccess(userApi: UserApi,
                             successLogin: () -> Unit,
                             errorLogin: (t: Throwable) -> Unit
    ): Job = launch(CommonPool) {
        try {
            val response: Response<AuthRequest> = service.authentication(userApi).await()

            if (!response.isSuccessful) {
                errorLogin(Exception(LoginFieldState.loginInvalid()))
                return@launch
            }

            if (response.body()?.success != true) {
                errorLogin(Exception(LoginFieldState.loginInvalid()))
                return@launch
            }

            val headers = response.headers()

            app.putSharedPreferences(
                    keyToAccess = "headers",
                    keys = mapOf(
                            Pair("access-token", headers["access-token"] ?: ""),
                            Pair("uid", headers["uid"] ?: ""),
                            Pair("client", headers["client"] ?: "")
                    )
            )
            successLogin()


        } catch (httpException: HttpException) {
            errorLogin(Exception(httpException.message()))
        } catch (ioException: IOException) {
            errorLogin(Exception(ioException.message))
        }

    }
}
