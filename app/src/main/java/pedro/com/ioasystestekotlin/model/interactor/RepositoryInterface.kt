package pedro.com.ioasystestekotlin.model.interactor

import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.Enterprise
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.User
import retrofit2.Response

interface RepositoryInterface {
    fun authentication(user: User,
                       successLogin: () -> Unit,
                       errorLogin: (t: Throwable) -> Unit
    ): DisposableObserver<Response<AuthRequest>>

    fun searchEnterprises(queryName: String,
                          searchFound: (List<Enterprise>) -> Unit,
                          errorSearch: (t: Throwable) -> Unit
    ): DisposableObserver<Response<ListEnterprises>>
}