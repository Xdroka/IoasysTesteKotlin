package pedro.com.ioasystestekotlin.model.data

import android.databinding.BaseObservable
import android.databinding.Bindable

data class EnabledChange(
        var enableChange: Boolean = false
): BaseObservable(){
    var _enableChange
    @Bindable
        get() = enableChange
        set(value) {
            enableChange = value
            notifyChange()
        }
}
