package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.EnabledChange
import pedro.com.ioasystestekotlin.model.data.Enterprise

class EnterpriseListViewModel {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = Enterprise()
    }
    var changeActivity = MutableLiveData<EnabledChange>().also {e ->
        e.value = EnabledChange()
    }

    fun onClickItem(){
        changeActivity.postValue(EnabledChange(true))
    }
}