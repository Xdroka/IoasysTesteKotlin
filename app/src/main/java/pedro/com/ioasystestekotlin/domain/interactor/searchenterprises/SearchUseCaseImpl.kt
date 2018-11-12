package pedro.com.ioasystestekotlin.domain.interactor.searchenterprises

import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.data.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.data.remote.searchenterprises.SearchEnterprises
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import retrofit2.Response

class SearchUseCaseImpl(private val search: SearchEnterprises) : SearchUseCase {

    private lateinit var disposeSearchUseCase: DisposableObserver<Response<ListEnterprises>>

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit) {
        disposeSearchUseCase = search
                .searchEnterprise(
                        query = query, searchFound = searchFound, errorSearch = errorSearch
                )
    }

    override fun disposeSearch() {
        disposeSearchUseCase.dispose()
    }
}