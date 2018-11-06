package pedro.com.ioasystestekotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.model.data.*
import pedro.com.ioasystestekotlin.model.interactor.Repository
import retrofit2.Response

class HomeViewModel(aplication: Application) : AndroidViewModel(aplication) {
    var searchField = MutableLiveData<StringObservable>().also {
        it.value = StringObservable()
    }
    private var state = MutableLiveData<ViewState<List<Enterprise>>>().also { viewState ->
        viewState.value = ViewState(null, State.WAITING_DATA)
    }
    var enterpriseList = MutableLiveData<List<Enterprise>>().also { list ->
        list.value = ArrayList()
    }
    private val repository = Repository(aplication)
    private lateinit var header: HeaderApi
    private var homeSubscribe: HomeSubscriber? = null

    fun searchListener() {
        val searchableEnterprises = searchField.value?.text ?: ""
        state.postValue(ViewState(null, State.LOADING))
//        api.searchEnterprises(searchableEnterprises, state, enterpriseList)
        homeSubscribe = repository
                .searchEnterprises(searchableEnterprises)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(HomeSubscriber())
    }

    inner class HomeSubscriber : DisposableObserver<Response<ListEnterprises>>() {
        override fun onComplete() {

        }

        override fun onNext(response: Response<ListEnterprises>) {
            if (!response.isSuccessful) {
                state.postValue(ViewState.failure(Exception("HTTP: ${response.code()} - ${response.message()} ")))
                return
            }

            response.body()?.enterprises?.let {list ->
                state.postValue(ViewState.success(list))
            }
//            state.postValue(ViewState.success(listOf(Enterprise())))
        }

        override fun onError(exception: Throwable) {
            state.postValue(ViewState.failure(exception))
        }
    }

    fun getState() = state

    fun setHeader(token: String, uid: String, client: String) {
        header = HeaderApi(token, uid, client)
    }

}