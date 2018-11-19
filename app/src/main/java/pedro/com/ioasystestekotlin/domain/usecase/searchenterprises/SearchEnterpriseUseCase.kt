package pedro.com.ioasystestekotlin.domain.usecase.searchenterprises

import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface SearchEnterpriseUseCase {
    fun searchEnterprise(query: String,
                         searchFound: (List<Enterprise>) -> Unit,
                         errorSearch: (t: Throwable) -> Unit)

    fun cancelJob()
}