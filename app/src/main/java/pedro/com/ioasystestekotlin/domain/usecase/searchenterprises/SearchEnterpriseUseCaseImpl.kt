package pedro.com.ioasystestekotlin.domain.usecase.searchenterprises

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pedro.com.ioasystestekotlin.data.remote.repository.enterprise.EnterpriseRepository
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class SearchEnterpriseUseCaseImpl(private val search: EnterpriseRepository) : SearchEnterpriseUseCase {

    private var jobSearch: Job = Job()

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit) {
        jobSearch = CoroutineScope(Dispatchers.IO)
                .launch {
                    search
                            .searchEnterprise(
                                    query = query, searchFound = searchFound, errorSearch = errorSearch
                            )
                }
    }

    override fun cancelJob() {
        jobSearch.cancel()
    }
}