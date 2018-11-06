package pedro.com.ioasystestekotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.HeaderApi
import pedro.com.ioasystestekotlin.model.dataclass.User
import pedro.com.ioasystestekotlin.model.interactor.Repository
import pedro.com.ioasystestekotlin.model.interactor.RepositoryInterface
import pedro.com.ioasystestekotlin.util.validateEmail
import pedro.com.ioasystestekotlin.util.validatePassword
import retrofit2.Response

class LoginViewModel(application: Application/*, repository: RepositoryInterface*/) : AndroidViewModel(application), LifecycleObserver {
    private var mRepository = Repository(application)
    private var mUser = MutableLiveData<User>().also {
        it.value = User()
    }
    private var mState = MutableLiveData<ViewState<HeaderApi>>().also { state ->
        state.value = ViewState.initializing()
    }
    private var mLoginSubscribe: LoginSubscriber? = null

    fun onClick() {
        teste()

        val email = mUser.value?._email ?: ""
        val password = mUser.value?._password ?: ""
        val isEmail = email.validateEmail()
        val isPassword = password.validatePassword()

        if (isEmail && isPassword) {

            mState.postValue(ViewState.loading())

            mLoginSubscribe = mRepository
                    .authentication(User(email, password))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(LoginSubscriber())
            return
        }

        mState.postValue(ViewState.failure(
                Exception(
                        when (isEmail) {
                            true -> "passwordInvalid"
                            false -> {
                                when (isPassword) {
                                    true -> "emailInvalid"
                                    false -> "bothInvalid"
                                }
                            }
                        }
                )
        ))
    }

    inner class LoginSubscriber : DisposableObserver<Response<AuthRequest>>() {
        override fun onComplete() {}

        override fun onNext(response: Response<AuthRequest>) {
            if (response.body()?.success != true) {
                mState.postValue(ViewState.failure(java.lang.Exception("loginInvalid")))
                return
            }

            val headers = response.headers()
            mState.postValue(
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
            mState.postValue(ViewState.failure(exception))
        }
    }

    fun getUser() = mUser

    fun getState() = mState

    private fun teste() {
        mUser.value?._email = "testeapple@ioasys.com.br"
        mUser.value?._password = "12341234"
    }

    override fun onCleared() {
        mLoginSubscribe?.dispose()
        super.onCleared()
    }
}
