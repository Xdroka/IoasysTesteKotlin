package pedro.com.ioasystestekotlin.domain.interactor.infoUseCase

import pedro.com.ioasystestekotlin.domain.model.Enterprise

interface InfoEnterpriseUseCase {

    fun getEnterprise(): Enterprise
}