package pedro.com.ioasystestekotlin.model.api

import io.reactivex.Flowable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Response
import retrofit2.http.*

interface WebService {
    @POST(PATH_SIGN_IN)
    fun login(@Body user: User): Flowable<Response<AuthRequest>>

    @GET(PATH_ENTERPRISES)
    fun searchEnterprise(@Query(FIELD_NAME) nameSearchable: String,
                        @HeaderMap headers: HashMap<String, String>
    ): Flowable<List<Enterprise>>

    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"

        const val PATH_SIGN_IN = "users/auth/sign_in"
        const val PATH_ENTERPRISES = "enterprises"

        const val FIELD_NAME = "name"

    }
}