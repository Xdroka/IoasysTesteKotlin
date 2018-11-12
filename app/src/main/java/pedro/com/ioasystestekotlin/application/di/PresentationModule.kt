package pedro.com.ioasystestekotlin.application.di

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.presentation.home.HomeViewModel
import pedro.com.ioasystestekotlin.presentation.login.LoginViewModel

val presentationModule = module {
    //Login View Model
    viewModel{ LoginViewModel(get(), get ()) }

    //Home View Model
    viewModel{ HomeViewModel(get(), get()) }

    //About View Model
//    viewModel { AboutViewModel(get()) }
}