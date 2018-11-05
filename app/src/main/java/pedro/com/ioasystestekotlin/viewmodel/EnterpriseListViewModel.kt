package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.data.Enterprise

class EnterpriseListViewModel : ViewModel() {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = Enterprise()
    }
    var viewState = MutableLiveData<ViewState<Enterprise>>().also {
//        it.value = ViewState(Enterprise(), Status.WAITING_DATA)
    }

    fun onClickItem(){
//        viewState.postValue(ViewState(enterprise.value, Status.SUCCESS))
    }
}