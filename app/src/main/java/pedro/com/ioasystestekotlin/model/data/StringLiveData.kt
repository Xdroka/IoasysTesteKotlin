package pedro.com.ioasystestekotlin.model.data

import android.databinding.BaseObservable
import android.databinding.Bindable

data class StringLiveData(var text: String = "")
    : BaseObservable() {

    var _text: String
    @Bindable
    get() = text
    set(value) {
        text = value
        notifyChange()
    }
}
