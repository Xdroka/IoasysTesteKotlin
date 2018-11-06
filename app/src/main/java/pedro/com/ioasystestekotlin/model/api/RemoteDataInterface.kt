package pedro.com.ioasystestekotlin.model.api

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.ListEnterprises
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Response

interface RemoteDataInterface {
    fun authentication(user: User): Observable<Response<AuthRequest>>?

    fun searchEnterprises(queryName: String,
                          headerApi: HeaderApi): Observable<Response<ListEnterprises>>
}