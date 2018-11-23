package pedro.com.ioasystestekotlin.application.di

import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.data.cache.room.repo.HeaderRoom
import pedro.com.ioasystestekotlin.data.cache.room.repo.HeaderRoomImpl
import pedro.com.ioasystestekotlin.data.remote.repository.enterprise.EnterpriseRepository
import pedro.com.ioasystestekotlin.data.remote.repository.enterprise.EnterpriseRepositoryImpl
import pedro.com.ioasystestekotlin.data.remote.repository.login.LoginRepository
import pedro.com.ioasystestekotlin.data.remote.repository.login.LoginRepositoryImpl
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

    // LoginRepository request
    single { LoginRepositoryImpl(get()) as  LoginRepository }

    // Search enterprises request
    single { EnterpriseRepositoryImpl(get()) as EnterpriseRepository }

    //header Repository in room
    single { HeaderRoomImpl() as HeaderRoom }
}