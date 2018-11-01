package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.Enterprise

class AboutViewModel(enterpriseArgument: Enterprise) {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = enterpriseArgument
    }
    var viewState = MutableLiveData<ViewState<String>>().also {
        it.value = ViewState(null, State.WAITING_DATA)
    }

    fun loadImage() {
        if (enterprise.value?.photo == null) {
            viewState.postValue(ViewState(null, State.FAILURE))
            return
        }
        viewState.postValue(ViewState(enterprise.value?.photo, State.GETTING_DATA))
    }

}