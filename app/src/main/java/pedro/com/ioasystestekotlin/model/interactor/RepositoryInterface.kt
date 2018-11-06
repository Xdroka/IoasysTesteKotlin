package pedro.com.ioasystestekotlin.model.interactor

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.ListEnterprises
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Response

interface RepositoryInterface {
    fun authentication(user: User): Observable<Response<AuthRequest>>

    fun searchEnterprises(queryName: String)
    :Observable<Response<ListEnterprises>>
}