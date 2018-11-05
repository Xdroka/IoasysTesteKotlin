package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.data.Enterprise

class AboutViewModel : ViewModel() {
    var enterprise = MutableLiveData<Enterprise>()
    var viewState = MutableLiveData<ViewState<String>>()

    fun setEnterprise(enterpriseArgument: Enterprise){
        enterprise.value = enterpriseArgument
    }

    fun loadImage() {
        if (enterprise.value?.photo == null) {
//            viewState.postValue(ViewState(null, Status.FAILURE))
            return
        }
//        viewState.postValue(ViewState(enterprise.value?.photo, Status.GETTING_DATA))
    }

}