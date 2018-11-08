package pedro.com.ioasystestekotlin.application

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import pedro.com.ioasystestekotlin.model.interactor.Repository
import pedro.com.ioasystestekotlin.model.interactor.RepositoryInterface
import pedro.com.ioasystestekotlin.viewmodel.HomeViewModel
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel

val appModule = module {

    single { Repository(get()) as RepositoryInterface }

    viewModel{LoginViewModel(get(), get())}
    viewModel{HomeViewModel(get(), get())}
}
