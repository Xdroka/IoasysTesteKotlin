package pedro.com.ioasystestekotlin.model.interactor

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.remote.RemoteDataStoreImpl
import retrofit2.Response

class EnterprisesInteractorImpl : EnterprisesInteractor {
    private val remoteDataStore = RemoteDataStoreImpl()

    override fun login(email: String,
                       password: String): Observable<Response<AuthRequest>> {
        return remoteDataStore.login(email, password)
    }

    override fun searchEnterprises(query: String, headerApi: HeaderApi): Observable<List<Enterprise>> {
        return remoteDataStore.searchEnterprises(query, headerApi)
    }
}