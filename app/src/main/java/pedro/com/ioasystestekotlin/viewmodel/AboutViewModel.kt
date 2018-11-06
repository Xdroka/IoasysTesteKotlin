package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.dataclass.Enterprise

class AboutViewModel : ViewModel() {
    var enterprise = MutableLiveData<Enterprise>()
    private var mViewState = MutableLiveData<ViewState<String>>().also {
        it.value = ViewState(null, State.WAITING_DATA)
    }

    fun getState() = mViewState

    fun setEnterprise(enterpriseArgument: Enterprise){
        enterprise.value = enterpriseArgument
    }

    fun loadImage() {
        if (enterprise.value?.photo == null) {
            mViewState.postValue(ViewState(null, State.FAILURE))
            return
        }
        mViewState.postValue(ViewState(enterprise.value?.photo, State.GETTING_DATA))
    }

}