package pedro.com.ioasystestekotlin.model.data

import android.databinding.BaseObservable
import android.databinding.Bindable

data class HeaderApi(var access_token: String?,
                     var uid: String?,
                     var client: String?
) : BaseObservable() {

    var _access_token
        @Bindable
        get() = access_token
        set(value) {
            access_token = value
            notifyChange()
        }

    var _uid
        @Bindable
        get() = uid
        set(value) {
            uid = value
            notifyChange()
        }

    var _client
        @Bindable
        get() = client
        set(value) {
            client = value
            notifyChange()
        }
}