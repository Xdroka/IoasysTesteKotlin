package pedro.com.ioasystestekotlin.model.data

import android.arch.lifecycle.MutableLiveData

class Observables{
    var messageToast: MutableLiveData<StringLiveData> = MutableLiveData<StringLiveData>().also {
        it.value = StringLiveData()
    }
    var changeActivity: MutableLiveData<EnabledChange> = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange()
    }
}
