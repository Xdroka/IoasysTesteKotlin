package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.data.Enterprise

class AboutViewModel : ViewModel() {
    var enterprise = MutableLiveData<Enterprise>()
    var viewState = MutableLiveData<ViewState<String>>().also {
        it.value = ViewState(null, State.WAITING_DATA)
    }

    fun setEnterprise(enterpriseArgument: Enterprise){
        enterprise.value = enterpriseArgument
    }

    fun loadImage() {
        if (enterprise.value?.photo == null) {
            viewState.postValue(ViewState(null, State.FAILURE))
            return
        }
        viewState.postValue(ViewState(enterprise.value?.photo, State.GETTING_DATA))
    }

}