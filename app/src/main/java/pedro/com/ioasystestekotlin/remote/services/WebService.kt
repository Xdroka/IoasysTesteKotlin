package pedro.com.ioasystestekotlin.remote.services

import io.reactivex.Flowable
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.User
import retrofit2.Response
import retrofit2.http.*

interface WebService {
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