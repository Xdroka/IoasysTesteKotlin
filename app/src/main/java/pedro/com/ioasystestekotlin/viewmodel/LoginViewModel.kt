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

        state.postValue(
                if (isEmail && isPassword) {
                    ViewState<String>(null, State.WAITING_DATA)
                } else {
                    when (isEmail) {
                        true -> ViewState("password", State.FAILURE)
                        false -> ViewState("email", State.FAILURE)
                    }
                }
        )

        if (state.value?.state == State.LOADING) {
            return
        }

        if (isEmail && isPassword) {
            state.postValue(ViewState(null, State.LOADING))
            api.auth(User(email, password), state)
        }
    }
}


