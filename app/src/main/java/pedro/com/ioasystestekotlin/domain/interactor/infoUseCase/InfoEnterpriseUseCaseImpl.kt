package pedro.com.ioasystestekotlin.domain.interactor.infoUseCase

import android.app.Application
import pedro.com.ioasystestekotlin.domain.ext.getEnterprise
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class InfoEnterpriseUseCaseImpl(val app: Application): InfoEnterpriseUseCase {

    override fun getEnterprise(): Enterprise = app.getEnterprise()

}