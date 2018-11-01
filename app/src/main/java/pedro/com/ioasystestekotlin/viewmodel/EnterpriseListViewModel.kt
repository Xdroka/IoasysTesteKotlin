package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.media.Image
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.util.ImageUtil

class EnterpriseListViewModel {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = Enterprise()
    }
    var viewState = MutableLiveData<ViewState<Enterprise>>().also {
        it.value = ViewState(Enterprise(), State.WAITING_DATA)
    }

    fun onClickItem(){
        viewState.postValue(ViewState(enterprise.value, State.SUCCESS))
    }
}