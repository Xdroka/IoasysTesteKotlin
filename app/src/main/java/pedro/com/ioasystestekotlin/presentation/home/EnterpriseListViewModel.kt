package pedro.com.ioasystestekotlin.presentation.home

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class EnterpriseListViewModel : ViewModel() {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = Enterprise()
    }

}