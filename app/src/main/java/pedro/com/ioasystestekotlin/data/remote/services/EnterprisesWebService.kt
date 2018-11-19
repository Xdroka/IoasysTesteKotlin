package pedro.com.ioasystestekotlin.data.remote.services

import kotlinx.coroutines.Deferred
import pedro.com.ioasystestekotlin.data.remote.model.ListEnterprises
import retrofit2.Response
import retrofit2.http.*

interface EnterprisesWebService {
   @GET(ENTERPRISES_PATH)
    fun searchEnterprise(@Query(QUERY_NAME) nameSearchable: String,
                         @HeaderMap headers: Map<String, String>
    ): Deferred<Response<ListEnterprises>>

    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br"
        const val ENTERPRISES_PATH = "enterprises"
        const val QUERY_NAME = "name"
    }

}