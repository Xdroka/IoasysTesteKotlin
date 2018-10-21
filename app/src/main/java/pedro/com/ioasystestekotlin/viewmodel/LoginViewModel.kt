package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.EnabledChange
import pedro.com.ioasystestekotlin.model.data.ErrorMessage
import pedro.com.ioasystestekotlin.model.data.User
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<User>().also {
        it.value = User()
    }
    var message = MutableLiveData<ErrorMessage>().also {
        it.value = ErrorMessage()
    }
    var changeActivity = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
    var buttonEnabled = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange(true)
    }
    var errorLogin = MutableLiveData<ErrorMessage>().also {
        it.value = ErrorMessage()
    }
    var api: ApiConnection = ApiConnection()

    fun onClick() {
        val email = user.value?._email ?: ""
        val password = user.value?._password ?: ""

        if (email == ""
                || password == ""
                || buttonEnabled.value?._enableChange != true) {
            return
        }

        if (isEmail(email)) {
            errorLogin.value?._errorMessage = ""
            buttonEnabled.postValue(EnabledChange(false))
            api.auth(
                    User(email, password),
                    message,
                    changeActivity,
                    buttonEnabled
            )
        }
        else{
            errorLogin.value?._errorMessage = "Login Ínvalido"
        }
    }

    private fun isEmail(email: String?): Boolean {
        val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).find()
    }
}

