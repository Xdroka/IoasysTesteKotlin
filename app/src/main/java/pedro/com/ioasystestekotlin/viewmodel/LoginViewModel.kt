package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.User
import pedro.com.ioasystestekotlin.model.util.UtilsData

class LoginViewModel : ViewModel() {
    var user = MutableLiveData<User>().also {
        it.value = User()
    }
    var state = MutableLiveData<ViewState<String>>().also { state ->
        state.value = ViewState(null, State.WAITING_DATA)
    }
    var api: ApiConnection = ApiConnection()

    init {
        api.auth(User("testeapple@ioasys.com.br", "12341234"), state)
    }

    fun onClick() {
        val email = user.value?._email ?: ""
        val password = user.value?._password ?: ""
        val isEmail = UtilsData.isEmail(email)
        val isPassword = UtilsData.isPassword(password)

        if (isEmail && isPassword) {
            state.postValue(ViewState(null, State.LOADING))
            api.auth(User(email, password), state)
            return
        }
        when (isEmail) {
            true -> state.postValue(ViewState("password", State.FAILURE))
            false -> state.postValue(ViewState("email", State.FAILURE))
        }

    }
}
