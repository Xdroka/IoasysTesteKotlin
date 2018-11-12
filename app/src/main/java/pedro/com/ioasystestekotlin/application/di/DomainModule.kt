package pedro.com.ioasystestekotlin.application.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.data.interactor.Repository
import pedro.com.ioasystestekotlin.data.interactor.RepositoryInterface


val domainModule = module{
    //Repository
    single { Repository(get()) as RepositoryInterface }
}