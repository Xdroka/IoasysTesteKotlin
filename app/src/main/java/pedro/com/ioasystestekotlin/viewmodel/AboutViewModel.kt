package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.Enterprise

class AboutViewModel(enterpriseArgument: Enterprise) {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = enterpriseArgument
    }
    var reload = MutableLiveData<Boolean>().also {
        it.value = false
    }

    fun reloadImage() {
        reload.postValue(enterprise.value?.photo != null)
    }

}