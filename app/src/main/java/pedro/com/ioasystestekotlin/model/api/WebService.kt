package pedro.com.ioasystestekotlin.model.api

import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {

    @POST("users/auth/sign_in")
    fun authentification(@Body user: User): Call<AuthRequest>

}