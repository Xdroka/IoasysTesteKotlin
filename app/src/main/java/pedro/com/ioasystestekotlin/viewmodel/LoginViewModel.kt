package pedro.com.ioasystestekotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.User
import pedro.com.ioasystestekotlin.model.interactor.Repository
import pedro.com.ioasystestekotlin.util.validateEmail
import pedro.com.ioasystestekotlin.util.validatePassword
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {
    private var repository = Repository(application)
    private var user = MutableLiveData<User>().also {
        it.value = User()
    }
    private var state = MutableLiveData<ViewState<HeaderApi>>().also { state ->
        state.value = ViewState.initializing()
    }
    private var loginSubscribe: LoginSubscriber? = null

    fun onClick() {
        val email = user.value?._email ?: ""
        val password = user.value?._password ?: ""
        val isEmail = email.validateEmail()
        val isPassword = password.validatePassword()

        if (!(isEmail && isPassword)) {
            when (isEmail) {
                true -> state.postValue(ViewState.failure(Exception("passwordInvalid")))
                false -> state.postValue(ViewState.failure(Exception("emailInvalid")))
            }

            return
        }

        state.postValue(ViewState.loading())

        loginSubscribe = repository
                .authentication(User(email, password))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(LoginSubscriber())
    }

    inner class LoginSubscriber : DisposableObserver<Response<AuthRequest>>() {
        override fun onComplete() {

        }

        override fun onNext(response: Response<AuthRequest>) {
            if(response.body()?.success != true) {
                state.postValue(ViewState.failure(java.lang.Exception("loginInvalid")))
                return
            }

            val headers = response.headers()
            state.postValue(
                    ViewState.success(
                            HeaderApi(
                                    access_token = headers["access-token"] ?: "",
                                    uid = headers["uid"] ?: "",
                                    client = headers["client"] ?: ""
                            )
                    )
            )
        }

        override fun onError(exception: Throwable) {
            state.postValue(ViewState.failure(exception))
        }
    }

    fun getUser() = user

    fun getState() = state

    //remove that
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun fillForm() {
        user.value?._email = "testeapple@ioasys.com.br"
        user.value?._password = "12341234"
        onClick()
    }

    override fun onCleared() {
        super.onCleared()
        loginSubscribe?.dispose()
    }
}
