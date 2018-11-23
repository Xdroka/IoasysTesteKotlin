package pedro.com.ioasystestekotlin.application.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.domain.usecase.info.InfoEnterpriseUseCase
import pedro.com.ioasystestekotlin.domain.usecase.info.InfoEnterpriseUseCaseImpl
import pedro.com.ioasystestekotlin.domain.usecase.login.LoginUseCase
import pedro.com.ioasystestekotlin.domain.usecase.login.LoginUseCaseImpl
import pedro.com.ioasystestekotlin.domain.usecase.searchenterprises.SearchEnterpriseUseCase
import pedro.com.ioasystestekotlin.domain.usecase.searchenterprises.SearchEnterpriseUseCaseImpl


val domainModule = module{
//    signIn use case
    single { LoginUseCaseImpl(get(), get()) as LoginUseCase }

//    search enterprises use case
    single { SearchEnterpriseUseCaseImpl(get(), get()) as SearchEnterpriseUseCase }

// info Enterprise use case
    single { InfoEnterpriseUseCaseImpl(get()) as InfoEnterpriseUseCase }

}