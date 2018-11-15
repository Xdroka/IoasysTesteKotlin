package pedro.com.ioasystestekotlin.domain.interactor.infoUseCase

import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface InfoEnterpriseUseCase {

    fun getEnterprise(): Enterprise
}