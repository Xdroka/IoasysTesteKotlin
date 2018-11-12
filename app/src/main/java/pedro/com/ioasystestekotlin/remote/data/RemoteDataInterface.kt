package pedro.com.ioasystestekotlin.remote.data

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.remote.model.UserApi
import retrofit2.Response

interface RemoteDataInterface {
    fun authentication(userApi: UserApi): Observable<Response<AuthRequest>>?

    fun searchEnterprises(queryName: String,
                          headerApi: HeaderApi): Observable<Response<ListEnterprises>>
}