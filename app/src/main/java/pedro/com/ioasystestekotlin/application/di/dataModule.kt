package pedro.com.ioasystestekotlin.application.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.data.remote.repository.login.Login
import pedro.com.ioasystestekotlin.data.remote.repository.login.LoginImpl
import pedro.com.ioasystestekotlin.data.remote.repository.enterprise.EnterpriseRepository
import pedro.com.ioasystestekotlin.data.remote.repository.enterprise.EnterpriseRepositoryImpl
import pedro.com.ioasystestekotlin.data.remote.services.EnterprisesWebService
import pedro.com.ioasystestekotlin.data.remote.services.UserWebService
import pedro.com.ioasystestekotlin.data.remote.services.createWebService
import pedro.com.ioasystestekotlin.data.remote.services.getBaseUrl

val dataModule = module{
    // Base URL
    single{ getBaseUrl() }

    //User Web Service
    single { createWebService<UserWebService>(get()) }

    //Enterprises web service
    single { createWebService<EnterprisesWebService>(get()) }

    // Login request
    single { LoginImpl(get(), get()) as  Login }

    // Search enterprises request
    single { EnterpriseRepositoryImpl(get(), get()) as EnterpriseRepository }

}