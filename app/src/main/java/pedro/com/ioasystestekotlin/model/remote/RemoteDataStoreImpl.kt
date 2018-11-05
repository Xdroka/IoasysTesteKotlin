package pedro.com.ioasystestekotlin.model.remote

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.api.WebService
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Response

class RemoteDataStoreImpl : RemoteDataStore {
    private val webService = createWebService<WebService>(WebService.BASE_URL)

    override fun login(email: String, password: String): Observable<Response<AuthRequest>> {
        return webService.login(
                User(
                        email = email,
                        password = password
                )
        ).toObservable()
    }

    override fun searchEnterprises(query: String, headerApi: HeaderApi): Observable<List<Enterprise>> {
        return webService.searchEnterprise(query,
                hashMapOf(
                        "access_token" to headerApi.access_token,
                        "uid" to headerApi.uid,
                        "client" to headerApi.client
                )
        ).toObservable()
    }
}
