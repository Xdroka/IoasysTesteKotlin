package pedro.com.ioasystestekotlin.model.data

import android.databinding.BaseObservable
import android.databinding.Bindable

data class MessageError(var errorMessage: String = "") : BaseObservable() {

    var _errorMessage: String
        @Bindable get() = errorMessage
        set(value) {
            errorMessage = value
            notifyChange()
        }
}
