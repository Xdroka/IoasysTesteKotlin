package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.remote.RemoteDataStore
import pedro.com.ioasystestekotlin.model.data.*

class HomeViewModel : ViewModel() {
    var searchField = MutableLiveData<StringObservable>().also {
        it.value = StringObservable()
    }
    var state = MutableLiveData<ViewState<String>>()
    var enterpriseList = MutableLiveData<List<Enterprise>>().also { list ->
        list.value = ArrayList()
    }
    lateinit var api: RemoteDataStore

    fun setHeader(token: String, uid: String, client: String) {
//        api = RemoteDataStore()
//        api.header = HeaderApi(token, uid, client)
    }

    fun searchListener() {
//        val searchableEnterprises = searchField.value?.text ?: ""
//        state.postValue(ViewState(null, Status.LOADING))
//        api.searchEnterprises(searchableEnterprises, state, enterpriseList)
    }


}