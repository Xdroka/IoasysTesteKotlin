package pedro.com.ioasystestekotlin.model.data

import android.arch.lifecycle.MutableLiveData

class ObservablesFields{
    var message: MutableLiveData<StringLiveData> = MutableLiveData<StringLiveData>().also {
        it.value = StringLiveData()
    }
    var changeActivity: MutableLiveData<EnabledChange> = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
    var loadingVisibility = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
}
