package pedro.com.ioasystestekotlin.presentation.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.domain.ext.validateEmail
import pedro.com.ioasystestekotlin.domain.ext.validatePassword
import pedro.com.ioasystestekotlin.domain.interactor.sign.SignCaseUse
import pedro.com.ioasystestekotlin.presentation.model.User
import pedro.com.ioasystestekotlin.presentation.ViewState
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.failure
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.initializing
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.loading
import pedro.com.ioasystestekotlin.presentation.ViewState.Companion.success

class LoginViewModel(private val signCaseUse: SignCaseUse) : ViewModel() {
    private var mUser = MutableLiveData<User>()
    private var mState = MutableLiveData<ViewState<String>>()

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
            sendRequest(email, password)
            return
        }
        invalidateFields(isEmail, isPassword)
    }

    private fun sendRequest(email: String, password: String) {
        signCaseUse.sign(
                email = email, password = password,
                onSuccess = {
                    mState.postValue(success())
                },
                onErrorLogin = {
                    mState.postValue(failure(it))
                }
        )
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
        signCaseUse.cancelJob()
        super.onCleared()
    }
}
