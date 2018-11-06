package pedro.com.ioasystestekotlin.model.api

import io.reactivex.Flowable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.ListEnterprises
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface WebService {
/*
    @POST("users/auth/sign_in")
    fun authentication(@Body user: User): Call<AuthRequest>

    @GET("enterprises")
    fun findEnterprises(@Query("name") nameSearchable: String,
                        @HeaderMap headers: HashMap<String, String>
    ): Call<ListEnterprises>
*/

    @POST(AUTH_PATH)
    fun authentication(@Body user: User): Flowable<Response<AuthRequest>>

    @GET(ENTERPRISES_PATH)
    fun searchEnterprise(@Query(QUERY_NAME) nameSearchable: String,
                         @HeaderMap headers: HashMap<String, String>
    ): Flowable<Response<ListEnterprises>>

    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"

        const val AUTH_PATH = "users/auth/sign_in"
        const val ENTERPRISES_PATH = "enterprises"

        const val QUERY_NAME = "name"
    }

}