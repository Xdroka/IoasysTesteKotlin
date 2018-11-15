package pedro.com.ioasystestekotlin.data.remote.services

import kotlinx.coroutines.experimental.Deferred
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import retrofit2.Response
import retrofit2.http.*

interface WebService {
    @POST(AUTH_PATH)
    fun authentication(@Body userApi: UserApi): Deferred<Response<AuthRequest>>

    @GET(ENTERPRISES_PATH)
    fun searchEnterprise(@Query(QUERY_NAME) nameSearchable: String,
                         @HeaderMap headers: Map<String, String>
    ): Deferred<Response<ListEnterprises>>

    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"

        const val AUTH_PATH = "users/auth/sign_in"
        const val ENTERPRISES_PATH = "enterprises"

        const val QUERY_NAME = "name"
    }

}