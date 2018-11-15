package pedro.com.ioasystestekotlin.presentation.model

import android.databinding.BaseObservable
import android.databinding.Bindable

data class Enterprise(
        var name: String = "",
        var photo: String = "",
        var description: String = "",
        var country: String = "",
        var bussiness: String = ""
) : BaseObservable() {
    var _name
        @Bindable
        get() = name
        set(value) {
            this.name = value
            notifyChange()
        }

    var _description
        @Bindable
        get() = description
        set(value) {
            description = value
            notifyChange()
        }

    var _country
        @Bindable
        get() = country
        set(value) {
            country = value
            notifyChange()
        }

    var _bussiness
        @Bindable
        get() = bussiness
        set(value) {
            bussiness = value
            notifyChange()
        }

    var _photo
        @Bindable
        get() = photo
        set(value){
            photo = value
            notifyChange()
        }
}