package pedro.com.ioasystestekotlin.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.model.dataclass.*
import pedro.com.ioasystestekotlin.model.interactor.Repository
import retrofit2.Response

class HomeViewModel(app: Application) : AndroidViewModel(app) {
    var searchField = MutableLiveData<StringObservable>().also {
        it.value = StringObservable()
    }
    private var mState = MutableLiveData<ViewState<List<Enterprise>>>()
            .also { viewState ->
                viewState.value = ViewState(null, State.WAITING_DATA)
            }
    private val mRepository = Repository(app)
    private var mHomeSubscribe: HomeSubscriber? = null

    fun searchListener() {
        val searchableEnterprises = searchField.value?.text ?: ""
        mState.postValue(ViewState.loading())

        mHomeSubscribe = mRepository
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
                mState.postValue(ViewState.failure(
                        Exception("HTTP: ${response.code()} - ${response.message()} ")))
                return
            }

            response.body()?.enterprises?.let { list ->
                mState.postValue(ViewState.success(list))
                return
            }
            mState.postValue(ViewState.success(listOf(Enterprise())))
        }

        override fun onError(exception: Throwable) {
            mState.postValue(ViewState.failure(exception))
        }
    }

    fun getState() = mState


}