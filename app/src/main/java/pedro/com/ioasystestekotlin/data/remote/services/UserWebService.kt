package pedro.com.ioasystestekotlin.data.remote.services

import kotlinx.coroutines.Deferred
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserWebService {
    @POST(AUTH_PATH)
    fun authentication(@Body userApi: UserApi): Deferred<Response<AuthRequest>>

    companion object {
        const val AUTH_PATH = "users/auth/sign_in"
    }
}