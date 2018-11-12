package pedro.com.ioasystestekotlin.presentation.about

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.domain.interactor.infoUseCase.InfoEnterpriseUseCase
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.ViewState

class AboutViewModel(app: Application, infoUseCase: InfoEnterpriseUseCase) : AndroidViewModel(app) {
    private var enterprise = MutableLiveData<Enterprise>()
    private var mViewState = MutableLiveData<ViewState<String>>()

    init {
        enterprise.value = infoUseCase.getEnterprise()
        mViewState.value = ViewState(null, State.WAITING_DATA)
    }

    fun getState(): LiveData<ViewState<String>> = mViewState

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