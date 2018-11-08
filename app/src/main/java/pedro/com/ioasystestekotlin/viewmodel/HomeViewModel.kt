package pedro.com.ioasystestekotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.model.dataclass.Enterprise
import pedro.com.ioasystestekotlin.model.dataclass.ListEnterprises
import pedro.com.ioasystestekotlin.model.dataclass.StringObservable
import pedro.com.ioasystestekotlin.model.interactor.RepositoryInterface
import retrofit2.Response

class HomeViewModel(app: Application, repository: RepositoryInterface) : AndroidViewModel(app) {
    var searchField = MutableLiveData<StringObservable>().also {
        it.value = StringObservable()
    }
    private var mState = MutableLiveData<ViewState<List<Enterprise>>>()
            .also { viewState ->
                viewState.value = ViewState(null, State.WAITING_DATA)
            }
    private val mRepository = repository
    private lateinit var mHomeSubscribe: DisposableObserver<Response<ListEnterprises>>

    fun searchListener() {
        val searchableEnterprises = searchField.value?.text ?: ""
        mState.postValue(ViewState.loading())

        mHomeSubscribe = mRepository
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

    fun getState() = mState

    override fun onCleared() {
        mHomeSubscribe.dispose()
        super.onCleared()
    }

}