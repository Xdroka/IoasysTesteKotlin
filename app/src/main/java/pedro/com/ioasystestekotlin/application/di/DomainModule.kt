package pedro.com.ioasystestekotlin.application.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.domain.interactor.infoUseCase.InfoEnterpriseUseCase
import pedro.com.ioasystestekotlin.domain.interactor.infoUseCase.InfoEnterpriseUseCaseImpl
import pedro.com.ioasystestekotlin.domain.interactor.searchenterprises.SearchUseCase
import pedro.com.ioasystestekotlin.domain.interactor.searchenterprises.SearchUseCaseImpl
import pedro.com.ioasystestekotlin.domain.interactor.sign.SignCaseUse
import pedro.com.ioasystestekotlin.domain.interactor.sign.SignCaseUseImpl


val domainModule = module{
//    sign use case
    single { SignCaseUseImpl(get()) as SignCaseUse }

//    search enterprises use case
    single { SearchUseCaseImpl(get()) as SearchUseCase }

// info Enterprise use case
    single { InfoEnterpriseUseCaseImpl(get()) as InfoEnterpriseUseCase }

}