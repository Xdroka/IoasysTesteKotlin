package pedro.com.ioasystestekotlin.model.dataclass

import android.databinding.BaseObservable
import android.databinding.Bindable

data class Enterprise(
        var enterprise_name: String? = "",
        var photo: String? = "",
        var description: String? = "",
        var country: String? = "",
        var enterprise_type: TypeEnterprise = TypeEnterprise()
) : BaseObservable() {
    var _name
        @Bindable
        get() = enterprise_name
        set(value) {
            this.enterprise_name = value
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
    get() = enterprise_type
    set(value) {
        enterprise_type = value
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