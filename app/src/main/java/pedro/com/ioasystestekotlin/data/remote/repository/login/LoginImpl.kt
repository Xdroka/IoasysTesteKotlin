package pedro.com.ioasystestekotlin.data.remote.repository.login

import android.app.Application
import kotlinx.coroutines.*
import pedro.com.ioasystestekotlin.data.ext.putSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.UserWebService
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class LoginImpl(val app: Application,
                private val userService: UserWebService) : Login {

    override suspend fun loginAccess(userApi: UserApi,
                             successLogin: () -> Unit,
                             errorLogin: (t: Throwable) -> Unit
    ): Job =

            CoroutineScope(Dispatchers.Default).launch {
        try {
            val response: Response<AuthRequest> = userService.authentication(userApi).await()

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
