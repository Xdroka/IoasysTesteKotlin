package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import pedro.com.ioasystestekotlin.model.data.*

class HomeViewModel : ViewModel() {
    var visibility = MutableLiveData<EnabledChange>().also {
        it.value = EnabledChange(true)
    }
    var searchField = MutableLiveData<StringLiveData>().also {
        it.value = StringLiveData()
    }
    var observables = Observables()
    var enterpriseList: List<Enterprise> = ArrayList()
    lateinit var headerApi: HeaderApi
    var api: ApiConnection = ApiConnection()

    fun setHeader(token: String, uid: String, client: String) {
        headerApi = HeaderApi(token, uid, client)
    }

    fun searchListener() {
        api.searchEnterprises(searchField.value?.text ?: "", observables.messageToast)
    }


}