package pedro.com.ioasystestekotlin.domain.interactor.searchenterprises

import kotlinx.coroutines.experimental.Job
import pedro.com.ioasystestekotlin.data.remote.searchenterprises.SearchEnterprises
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class SearchUseCaseImpl(private val search: SearchEnterprises) : SearchUseCase {

    private var jobSearch: Job = Job()

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit) {
        jobSearch = search
                .searchEnterprise(
                        query = query, searchFound = searchFound, errorSearch = errorSearch
                )
    }

    override fun cancelJob() {
        jobSearch.cancel()
    }
}