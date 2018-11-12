package pedro.com.ioasystestekotlin.data.remote.searchenterprises

import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.data.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import retrofit2.Response

interface SearchEnterprises  {
    fun searchEnterprise(query: String,
                         searchFound: (List<Enterprise>) -> Unit,
                         errorSearch: (t: Throwable) -> Unit
    ): DisposableObserver<Response<ListEnterprises>>
}