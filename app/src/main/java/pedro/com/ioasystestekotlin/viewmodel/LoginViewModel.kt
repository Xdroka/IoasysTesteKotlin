package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.EnabledChange
import pedro.com.ioasystestekotlin.model.data.ObservablesFields
import pedro.com.ioasystestekotlin.model.data.User
import pedro.com.ioasystestekotlin.model.util.UtilsData

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<User>().also {
        it.value = User()
    }
    var observables = ObservablesFields()
    var errorLoginEmail = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
    var errorLoginPassword = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
    var api: ApiConnection = ApiConnection()

    init {
        api.auth(User("testeapple@ioasys.com.br", "12341234"), observables)
    }

    fun onClick() {
        val email = user.value?._email ?: ""
        val password = user.value?._password ?: ""
        val isEmail = UtilsData.isEmail(email)
        val isPassword = UtilsData.isPassword(password)

        if (observables.loadingVisibility.value?.enableChange == true) {
            return
        }
        errorLoginEmail.postValue(EnabledChange(!isEmail))
        errorLoginPassword.postValue(EnabledChange(!isPassword))

        if (isEmail && isPassword) {
            observables.loadingVisibility.postValue(EnabledChange(true))
            api.auth(
                    User(email, password),
                    observables
            )
        }
    }
}


