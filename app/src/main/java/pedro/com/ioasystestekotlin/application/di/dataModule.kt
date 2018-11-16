package pedro.com.ioasystestekotlin.application.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.data.remote.sign.SignAuth
import pedro.com.ioasystestekotlin.data.remote.sign.SignAuthImpl
import pedro.com.ioasystestekotlin.data.remote.searchenterprises.SearchEnterprises
import pedro.com.ioasystestekotlin.data.remote.searchenterprises.SearchEnterprisesImpl
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.data.remote.services.createWebService

val dataModule = module{
    single{ WebService.BASE_URL }

    //web service
    single { createWebService<WebService>(get()) }

    // SignAuth request
    single { SignAuthImpl(get(), get()) as  SignAuth }

    // Search enterprises request
    single { SearchEnterprisesImpl(get(), get()) as SearchEnterprises }

}