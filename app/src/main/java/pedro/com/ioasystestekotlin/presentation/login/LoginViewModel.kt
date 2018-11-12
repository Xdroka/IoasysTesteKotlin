package pedro.com.ioasystestekotlin.presentation.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.domain.model.User
import pedro.com.ioasystestekotlin.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.interactor.RepositoryInterface
import pedro.com.ioasystestekotlin.presentation.ViewState
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.failure
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.initializing
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.loading
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.success
import pedro.com.ioasystestekotlin.domain.model.mapper.validateEmail
import pedro.com.ioasystestekotlin.domain.model.mapper.validatePassword
import retrofit2.Response

class LoginViewModel(application: Application,
                     repository: RepositoryInterface
) : AndroidViewModel(application), LifecycleObserver {

    private var mRepository = repository
    private var mUser = MutableLiveData<User>()
    private var mState = MutableLiveData<ViewState<String>>()
    private lateinit var mLoginSubscribe: DisposableObserver<Response<AuthRequest>>

    init {
        mUser.value = User()
        mState.value = initializing()
    }


    fun onClick() {
        setUserValid()

        val email = mUser.value?._email ?: ""
        val password = mUser.value?._password ?: ""
        val isEmail = email.validateEmail()
        val isPassword = password.validatePassword()

        if (isEmail && isPassword) {

            mState.postValue(loading())

            mLoginSubscribe = mRepository.authentication(
                    email = email, password =  password,
                    successLogin = {
                        mState.postValue(success())
                    },
                    errorLogin = {
                        mState.postValue(failure(it))
                    }
            )
            return
        }

        invalidateFields(isEmail, isPassword)
    }

    private fun invalidateFields(isEmail: Boolean, isPassword: Boolean) {
        mState.postValue(failure(
                Exception(
                        when (isEmail) {
                            true -> LoginFieldState.passwordError()
                            false -> {
                                when (isPassword) {
                                    true -> LoginFieldState.emailError()
                                    false -> LoginFieldState.bothError()
                                }
                            }
                        }
                )
        ))
    }


    fun getUser() = mUser

    fun getState(): LiveData<ViewState<String>> = mState

    private fun setUserValid() {
        mUser.value?._email = "testeapple@ioasys.com.br"
        mUser.value?._password = "12341234"
    }

    override fun onCleared() {
        mLoginSubscribe.dispose()
        super.onCleared()
    }
}
