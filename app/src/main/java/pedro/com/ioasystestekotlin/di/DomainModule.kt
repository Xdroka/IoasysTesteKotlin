package pedro.com.ioasystestekotlin.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.model.interactor.Repository
import pedro.com.ioasystestekotlin.model.interactor.RepositoryInterface


val domainModule = module{
    //Repository
    single { Repository(get()) as RepositoryInterface }
}