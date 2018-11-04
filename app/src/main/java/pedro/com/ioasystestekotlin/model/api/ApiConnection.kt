package pedro.com.ioasystestekotlin.model.api

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.*
import pedro.com.ioasystestekotlin.viewmodel.State
import pedro.com.ioasystestekotlin.viewmodel.ViewState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection {
    private var mRetrofit: Retrofit
    private var mService: WebService
    lateinit var header: HeaderApi

    init {
        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mService = mRetrofit.create(WebService::class.java)
    }

    fun auth(user: User, state: MutableLiveData<ViewState<String>>) {
        val call = mService.authentification(user)

        call.enqueue(object : Callback<AuthRequest> {
            override fun onFailure(call: Call<AuthRequest>, t: Throwable) {
                state.postValue(ViewState(ERROR_CONNECTION, State.GETTING_DATA))
            }

            override fun onResponse(call: Call<AuthRequest>, response: Response<AuthRequest>) {
                if (response.body()?.success == true) {
                    header = HeaderApi(
                            response.headers().get("access-token") ?: "",
                            response.headers().get("uid") ?: "",
                            response.headers().get("client") ?: ""
                    )
                    state.postValue(ViewState(null, State.SUCCESS))
                } else {
                    state.postValue(ViewState(ERROR_LOGIN, State.GETTING_DATA))
                }
            }

        })
    }

    fun searchEnterprises(enterpriseName: String,
                          state: MutableLiveData<ViewState<String>>, enterpriseList: MutableLiveData<List<Enterprise>>) {

        val headerHashMap = HashMap<String,String>()
        headerHashMap["access-token"] = header.access_token
        headerHashMap["client"] = header.client
        headerHashMap["uid"] = header.uid
        val call = mService.findEnterprises(enterpriseName, headerHashMap)

        call.enqueue(object : Callback<ListEnterprises> {
            override fun onFailure(call: Call<ListEnterprises>, t: Throwable) {
                state.postValue(ViewState(ERROR_CONNECTION, State.FAILURE))
            }

            override fun onResponse(call: Call<ListEnterprises>, response: Response<ListEnterprises>) {
                if (response.isSuccessful) {
                    enterpriseList.postValue(response.body()?.enterprises)
                    state.postValue(ViewState(null, State.SUCCESS))
                }
                else{
                    state.postValue(ViewState(null, State.WAITING_DATA))
                }

            }
        })

    }

    fun doLogin(onSuccess: () -> Unit, onFailure: () -> Unit) {
//        mService.authentification()
//                .
    }

    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"
        private const val ERROR_CONNECTION = "Falha na conexão"
        private const val ERROR_LOGIN = "Login Ínvalido"
    }

}
