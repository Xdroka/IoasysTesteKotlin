package pedro.com.ioasystestekotlin.model.interactor

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.User
import retrofit2.Response

interface RepositoryInterface {
    fun authentication(user: User): Observable<Response<AuthRequest>>

    fun searchEnterprises(queryName: String)
    :Observable<Response<ListEnterprises>>
}