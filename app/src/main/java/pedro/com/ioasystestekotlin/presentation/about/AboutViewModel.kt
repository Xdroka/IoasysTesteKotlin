package pedro.com.ioasystestekotlin.presentation.about

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import pedro.com.ioasystestekotlin.remote.model.EnterpriseApi
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.ViewState
import pedro.com.ioasystestekotlin.remote.model.mapper.convertEnterprise

class AboutViewModel : ViewModel() {
    private var enterprise = MutableLiveData<Enterprise>()
    private var mViewState = MutableLiveData<ViewState<String>>().also {
        it.value = ViewState(null, State.WAITING_DATA)
    }

    fun getState(): LiveData<ViewState<String>> = mViewState

    fun setEnterprise(enterpriseApiArgument: EnterpriseApi) {
        enterprise.value = enterpriseApiArgument.convertEnterprise()
    }

    fun getEnterprise() = enterprise

    fun loadImage() {
        if (enterprise.value?.photo == null) {
            mViewState.postValue(ViewState(null, State.FAILURE))
            return
        }
        enterprise.value?.photo?.let {
            mViewState.postValue(ViewState.gettingData(it))
        }
    }

}