package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.User
import pedro.com.ioasystestekotlin.model.interactor.EnterprisesInteractorImpl
import pedro.com.ioasystestekotlin.model.util.isEmail
import pedro.com.ioasystestekotlin.model.util.isPassword
import retrofit2.Response

class LoginViewModel : ViewModel(), LifecycleObserver {

    private val interactor = EnterprisesInteractorImpl()

    var user = MutableLiveData<User>().also { it.value = User() }
    private var viewState = MutableLiveData<ViewState<HeaderApi>>()
    private var loginSubscriber = LoginSubscriber()

    fun getState() = viewState

    fun onLoginClick() {
        val email = user.value?._email ?: ""
        val password = user.value?._password ?: ""

        if (!validForm(email, password)) {
            viewState.postValue(ViewState.error(Exception("Dados inválidos")))
            return
        }

        viewState.postValue(ViewState.loading())

        loginSubscriber = interactor
                .login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(LoginSubscriber())
    }

    inner class LoginSubscriber : DisposableObserver<Response<AuthRequest>>() {
        override fun onComplete() {}

        override fun onNext(t: Response<AuthRequest>) {
            val headers = t.headers()

            viewState.postValue(
                    ViewState.success(
                            HeaderApi(
                                    access_token = headers["access-token"] ?: "",
                                    uid = headers["uid"] ?: "",
                                    client = headers["client"] ?: ""
                            )
                    )
            )
        }

        override fun onError(e: Throwable) {
            viewState.postValue(ViewState.error(e))
        }
    }

    private fun validForm(email: String, password: String): Boolean {
        return email.isEmail() && password.isPassword()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fillForm() {
        user.value?._email = "testeapple@ioasys.com.br"
        user.value?._password = "12341234"
    }

    override fun onCleared() {
        super.onCleared()
        loginSubscriber.dispose()
    }

}
