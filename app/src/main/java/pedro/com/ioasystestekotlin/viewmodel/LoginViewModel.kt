package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.User

class LoginViewModel : ViewModel() {
    val user = MutableLiveData<User>().also {
        it.value = User("", "")
    }
}
