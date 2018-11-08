package pedro.com.ioasystestekotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.model.dataclass.AuthRequest
import pedro.com.ioasystestekotlin.model.dataclass.User
import pedro.com.ioasystestekotlin.model.interactor.RepositoryInterface
import pedro.com.ioasystestekotlin.util.validateEmail
import pedro.com.ioasystestekotlin.util.validatePassword
import retrofit2.Response

class LoginViewModel(application: Application,
                     repository: RepositoryInterface
) : AndroidViewModel(application), LifecycleObserver {

    private var mRepository = repository
    private var mUser = MutableLiveData<User>().also {
        it.value = User()
    }
    private var mState = MutableLiveData<ViewState<String>>().also { state ->
        state.value = ViewState.initializing()
    }
    private lateinit var mLoginSubscribe: DisposableObserver<Response<AuthRequest>>

    fun onClick() {
        teste()

        val email = mUser.value?._email ?: ""
        val password = mUser.value?._password ?: ""
        val isEmail = email.validateEmail()
        val isPassword = password.validatePassword()

        if (isEmail && isPassword) {

            mState.postValue(ViewState.loading())

            mLoginSubscribe = mRepository.authentication(
                    user = User(email, password),
                    successLogin = {
                        mState.postValue(ViewState.success())
                    },
                    errorLogin = {
                        mState.postValue(ViewState.failure(it))
                    }
            )
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


    fun getUser() = mUser

    fun getState() = mState

    private fun teste() {
        mUser.value?._email = "testeapple@ioasys.com.br"
        mUser.value?._password = "12341234"
    }

    override fun onCleared() {
        mLoginSubscribe.dispose()
        super.onCleared()
    }
}
