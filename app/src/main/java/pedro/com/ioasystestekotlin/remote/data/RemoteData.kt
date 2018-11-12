package pedro.com.ioasystestekotlin.remote.data

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.remote.model.UserApi
import pedro.com.ioasystestekotlin.remote.headerMapper
import pedro.com.ioasystestekotlin.remote.services.WebService
import pedro.com.ioasystestekotlin.remote.services.createWebService
import retrofit2.Response

class RemoteData : RemoteDataInterface {
    private var mService: WebService = createWebService()

    override fun authentication(userApi: UserApi)
            : Observable<Response<AuthRequest>> = mService.authentication(userApi).toObservable()

    override fun searchEnterprises(queryName: String,
                                   headerApi: HeaderApi): Observable<Response<ListEnterprises>> =
        mService.searchEnterprise(queryName, headerMapper(headerApi)).toObservable()

}
