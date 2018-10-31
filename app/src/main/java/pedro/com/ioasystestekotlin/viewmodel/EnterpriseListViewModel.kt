package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.Enterprise

class EnterpriseListViewModel {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = Enterprise()
    }
    var viewState = MutableLiveData<ViewState<String>>().also {
        it.value = ViewState(null, State.WAITING_DATA)
    }

    fun onClickItem(){
        viewState.postValue(ViewState(enterprise.value?.enterprise_name, State.SUCCESS))
    }
}