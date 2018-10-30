package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.*

class HomeViewModel : ViewModel() {
    var searchField = MutableLiveData<StringLiveData>().also {
        it.value = StringLiveData()
    }
    var observables = ObservablesFields()
    var enterpriseList = MutableLiveData<List<Enterprise>>().also { list ->
        list.value = ArrayList()
    }
    lateinit var api: ApiConnection

    fun setHeader(token: String, uid: String, client: String) {
        api = ApiConnection()
        api.header = HeaderApi(token, uid, client)
    }

    fun searchListener() {
        val searchableEnterprises = searchField.value?.text ?: ""
        observables.loadingVisibility.postValue(EnabledChange(true))
        api.searchEnterprises(searchableEnterprises, observables, enterpriseList)
    }


}