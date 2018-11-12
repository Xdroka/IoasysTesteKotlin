package pedro.com.ioasystestekotlin.data.interactor

import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import pedro.com.ioasystestekotlin.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.remote.model.ListEnterprises
import retrofit2.Response

interface RepositoryInterface {
    fun authentication(email: String, password: String,
                       successLogin: () -> Unit,
                       errorLogin: (t: Throwable) -> Unit
    ): DisposableObserver<Response<AuthRequest>>

    fun searchEnterprises(queryName: String,
                          searchFound: (List<Enterprise>) -> Unit,
                          errorSearch: (t: Throwable) -> Unit
    ): DisposableObserver<Response<ListEnterprises>>
}