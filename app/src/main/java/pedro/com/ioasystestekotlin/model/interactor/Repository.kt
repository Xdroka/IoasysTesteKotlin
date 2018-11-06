package pedro.com.ioasystestekotlin.model.interactor

import android.app.Application
import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.api.RemoteData
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.ListEnterprises
import pedro.com.ioasystestekotlin.model.data.User
import pedro.com.ioasystestekotlin.util.getHeader
import retrofit2.Response

class Repository(val app: Application) : RepositoryInterface {
    private val remoteData = RemoteData()

    override fun authentication(user: User): Observable<Response<AuthRequest>> =
            remoteData.authentication(user)

    override fun searchEnterprises(queryName: String): Observable<Response<ListEnterprises>> =
            remoteData.searchEnterprises(queryName,  app.applicationContext.getHeader() )

}