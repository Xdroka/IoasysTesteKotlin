package pedro.com.ioasystestekotlin.model.api

import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.ListEnterprises
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Call
import retrofit2.http.*

interface WebService {
    @POST("users/auth/sign_in")
    fun authentification(@Body user: User): Call<AuthRequest>

    @GET("enterprises")
    fun findEnterprises(@Query("name") nameSearchable: String,
                        @HeaderMap headers: HashMap<String, String>
    ): Call<ListEnterprises>
}