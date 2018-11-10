package pedro.com.ioasystestekotlin.presentation.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.model.dataclass.Enterprise
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.StringObservable
import pedro.com.ioasystestekotlin.model.interactor.RepositoryInterface
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.ViewState
import retrofit2.Response

class HomeViewModel(app: Application, repository: RepositoryInterface) : AndroidViewModel(app) {
    var searchField = MutableLiveData<StringObservable>()
    private var mState = MutableLiveData<ViewState<List<Enterprise>>>()
    private val mRepository = repository
    private lateinit var mHomeSubscribe: DisposableObserver<Response<ListEnterprises>>

    init {
        searchField.value = StringObservable()
        mState.value = ViewState(data = null ,state = State.WAITING_DATA)
    }

    fun searchListener() {
        val searchableEnterprises = searchField.value?.text ?: ""
        mState.postValue(ViewState.loading())

        mHomeSubscribe = getDisposeObserver(searchableEnterprises)
    }

    fun getState(): LiveData<ViewState<List<Enterprise>>> = mState

    private fun getDisposeObserver(searchableEnterprises: String): DisposableObserver<Response<ListEnterprises>> {
        return mRepository
                .searchEnterprises(
                        queryName = searchableEnterprises,
                        searchFound = { list ->
                            mState.postValue(ViewState.success(list))
                        },
                        errorSearch = { throwable ->
                            mState.postValue(ViewState.failure(throwable))
                        }
                )
    }

    override fun onCleared() {
        mHomeSubscribe.dispose()
        super.onCleared()
    }

}