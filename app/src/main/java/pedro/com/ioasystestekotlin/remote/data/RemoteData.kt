package pedro.com.ioasystestekotlin.remote.data

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.HeaderApi
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.User
import pedro.com.ioasystestekotlin.remote.headerMapper
import pedro.com.ioasystestekotlin.remote.services.WebService
import pedro.com.ioasystestekotlin.remote.services.createWebService
import retrofit2.Response

class RemoteData : RemoteDataInterface {
    private var mService: WebService = createWebService()

    override fun authentication(user: User)
            : Observable<Response<AuthRequest>> = mService.authentication(user).toObservable()

    override fun searchEnterprises(queryName: String,
                                   headerApi: HeaderApi): Observable<Response<ListEnterprises>> =
        mService.searchEnterprise(queryName, headerMapper(headerApi)).toObservable()

}
