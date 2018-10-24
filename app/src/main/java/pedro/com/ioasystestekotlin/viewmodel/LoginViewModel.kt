package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.EnabledChange
import pedro.com.ioasystestekotlin.model.data.StringLiveData
import pedro.com.ioasystestekotlin.model.data.User
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<User>().also {
        it.value = User()
    }
    var message = MutableLiveData<StringLiveData>().also {
        it.value = StringLiveData()
    }
    var changeActivity = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
    var buttonEnabled = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange(true)
    }
    var errorLogin = MutableLiveData<StringLiveData>().also {
        it.value = StringLiveData()
    }
    var api: ApiConnection = ApiConnection()

    init {
        api.auth(User("testeapple@ioasys.com.br", "12341234"),
                message,changeActivity,buttonEnabled)
    }

    fun onClick() {
        val email = user.value?._email ?: ""
        val password = user.value?._password ?: ""

        if (email == ""
                || password == ""
                || buttonEnabled.value?._enableChange != true) {
            return
        }

        if (isEmail(email)) {
            errorLogin.postValue(StringLiveData())
            buttonEnabled.postValue(EnabledChange(false))
            api.auth(
                    User(email, password),
                    message,
                    changeActivity,
                    buttonEnabled
            )
        }
        else{
            errorLogin.postValue(StringLiveData("Login Ínvalido"))
        }
    }

    private fun isEmail(email: String?): Boolean {
        val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).find()
    }
}

