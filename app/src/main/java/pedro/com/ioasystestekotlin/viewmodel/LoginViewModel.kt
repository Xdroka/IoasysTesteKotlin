package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.MessageError
import pedro.com.ioasystestekotlin.model.data.User
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<User>().also {
        it.value = User()
    }
    var message = MutableLiveData<MessageError>().also { it.value = MessageError() }
    var api: ApiConnection = ApiConnection()

    fun onClick() {
        var email = user.value?._email ?: ""
        var password = user.value?._password ?: ""

        if(email == "" || password == "") {
//            return
            email = "testeapp@ioasys.com.br"
            password = "12341234"
        }

        message.value?._errorMessage = "Teste"
        if (isEmail(email)) {
            api.auth(
                    User(
                        email,
                        password
                    ),
                    message
            )
        }
    }

    private fun isEmail(email: String?): Boolean {
        val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).find()
    }
}

