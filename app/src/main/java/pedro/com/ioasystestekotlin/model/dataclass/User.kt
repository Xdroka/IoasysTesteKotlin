package pedro.com.ioasystestekotlin.model.dataclass

import android.databinding.BaseObservable
import android.databinding.Bindable

data class User(var email: String = "",
                var password: String = ""
) : BaseObservable() {

    var _email: String
        @Bindable get() = email
        set(value) {
            email = value
            notifyChange()
        }

    var _password: String
        @Bindable get() = password
        set(value) {
            password = value
            notifyChange()
        }

}
