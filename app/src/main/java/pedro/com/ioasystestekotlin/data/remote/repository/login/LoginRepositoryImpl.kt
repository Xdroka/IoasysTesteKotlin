package pedro.com.ioasystestekotlin.data.remote.repository.login

import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.UserWebService
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState.Companion.loginInvalid
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class LoginRepositoryImpl(private val userService: UserWebService) : LoginRepository {

    override suspend fun loginAccess(userApi: UserApi): ResultRequest<Map<String, String>> {
        try {
            val response: Response<AuthRequest> = userService.authentication(userApi).await()

            if (!response.isSuccessful) {
                return ResultRequest.error(Exception(loginInvalid()))
            }

            if (response.body()?.success != true) {
                return ResultRequest.error(Exception(loginInvalid()))
            }

            var mapHeader: Map<String, String>

            response.headers().apply {
                mapHeader = mapOf(
                        "access-token" to (this["access-token"] ?: ""),
                        "uid" to (this["uid"] ?: ""),
                        "client" to (this["client"] ?: "")
                )
            }

            return ResultRequest.success(mapHeader)

        } catch (httpException: HttpException) {
            return ResultRequest.error(httpException as Exception)
        } catch (ioException: IOException) {
            return ResultRequest.error(ioException)
        }
    }
}
