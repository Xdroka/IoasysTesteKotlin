package pedro.com.ioasystestekotlin.domain.interactor.searchenterprises

import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface SearchUseCase {
    fun searchEnterprise(query: String,
                         searchFound: (List<Enterprise>) -> Unit,
                         errorSearch: (t: Throwable) -> Unit)

    fun cancelJob()
}