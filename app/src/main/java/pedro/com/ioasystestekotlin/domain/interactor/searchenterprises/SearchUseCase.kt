package pedro.com.ioasystestekotlin.domain.interactor.searchenterprises

import pedro.com.ioasystestekotlin.domain.model.Enterprise

interface SearchUseCase {
    fun searchEnterprise(query: String,
                         searchFound: (List<Enterprise>) -> Unit,
                         errorSearch: (t: Throwable) -> Unit)

    fun disposeSearch()
}