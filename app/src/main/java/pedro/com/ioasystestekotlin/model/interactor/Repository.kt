package pedro.com.ioasystestekotlin.model.interactor

import android.app.Application
import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.api.RemoteData
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.User
import pedro.com.ioasystestekotlin.util.getHeader
import retrofit2.Response

class Repository(val app: Application) : RepositoryInterface {
    private val mRemoteData = RemoteData()

    override fun authentication(user: User): Observable<Response<AuthRequest>> =
            mRemoteData.authentication(user)

    override fun searchEnterprises(queryName: String): Observable<Response<ListEnterprises>> =
            mRemoteData.searchEnterprises(queryName,  app.applicationContext.getHeader() )

}