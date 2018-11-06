package pedro.com.ioasystestekotlin.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import pedro.com.ioasystestekotlin.model.dataclass.Enterprise

class EnterpriseListViewModel : ViewModel() {
    var enterprise = MutableLiveData<Enterprise>().also {
        it.value = Enterprise()
    }

}