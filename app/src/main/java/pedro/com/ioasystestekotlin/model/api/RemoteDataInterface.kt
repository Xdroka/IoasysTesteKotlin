package pedro.com.ioasystestekotlin.model.api

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.HeaderApi
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.User
import retrofit2.Response

interface RemoteDataInterface {
    fun authentication(user: User): Observable<Response<AuthRequest>>?

    fun searchEnterprises(queryName: String,
                          headerApi: HeaderApi): Observable<Response<ListEnterprises>>
}