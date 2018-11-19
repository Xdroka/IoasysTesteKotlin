package pedro.com.ioasystestekotlin.domain.usecase.info

import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface InfoEnterpriseUseCase {

    fun getEnterprise(): Enterprise
}