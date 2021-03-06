package pedro.com.ioasystestekotlin.presentation.home

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.domain.usecase.searchenterprises.SearchEnterpriseUseCase
import pedro.com.ioasystestekotlin.presentation.model.Enterprise
import pedro.com.ioasystestekotlin.presentation.model.StringObservable
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.ViewState

class HomeViewModel(searchEnterprise: SearchEnterpriseUseCase) : ViewModel() {
    var searchField = MutableLiveData<StringObservable>()
    private var mState = MutableLiveData<ViewState<List<Enterprise>>>()
    private val mRepository = searchEnterprise

    init {
        searchField.value = StringObservable()
        mState.value = ViewState(data = null, state = State.WAITING_DATA)
    }

    fun searchListener() {
        val searchableEnterprises = searchField.value?.text ?: ""
        mState.postValue(ViewState.loading())

        mRepository.searchEnterprise(
                query = searchableEnterprises,
                searchFound = { list ->
                    mState.postValue(ViewState.success(list))
                },
                errorSearch = { throwable ->
                    mState.postValue(ViewState.failure(throwable))
                }
        )
    }

    fun getState(): LiveData<ViewState<List<Enterprise>>> = mState

    override fun onCleared() {
        mRepository.cancelJob()
        super.onCleared()
    }

}