package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.User
import java.util.regex.Pattern

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<User>().also {
        it.value = User("", "")
    }

    fun onClick(){
        if(isEmail(user.value?.email) ){

        }
    }

    private fun isEmail(email: String?): Boolean {
        val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        return pattern.matcher(email).find()
    }
}
