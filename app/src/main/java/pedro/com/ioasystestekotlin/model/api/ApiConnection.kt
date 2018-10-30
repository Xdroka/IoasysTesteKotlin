package pedro.com.ioasystestekotlin.model.api

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.*
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

    fun auth(user: User, observablesFields: ObservablesFields) {
        val call = mService.authentification(user)

        call.enqueue(object : Callback<AuthRequest> {
            override fun onFailure(call: Call<AuthRequest>, t: Throwable) {
                observablesFields.message.postValue(StringLiveData(ERROR_CONNECTION))
                observablesFields.loadingVisibility.postValue(EnabledChange())
            }

            override fun onResponse(call: Call<AuthRequest>, response: Response<AuthRequest>) {
                if (response.body()?.success == true) {
                    header = HeaderApi(
                            response.headers().get("access-token") ?: "",
                            response.headers().get("uid") ?: "",
                            response.headers().get("client") ?: ""
                    )
                    observablesFields.changeActivity.postValue(EnabledChange(true))
                } else {
                           observablesFields.message.postValue(StringLiveData(ERROR_LOGIN))
                }
                observablesFields.loadingVisibility.postValue(EnabledChange())
            }

        })
    }

    fun searchEnterprises(enterpriseName: String,
                          observablesFields: ObservablesFields, enterpriseList: MutableLiveData<List<Enterprise>>) {

        val headerHashMap = HashMap<String,String>()
        headerHashMap["access-token"] = header.access_token
        headerHashMap["client"] = header.client
        headerHashMap["uid"] = header.uid
        val call = mService.findEnterprises(enterpriseName, headerHashMap)

        call.enqueue(object : Callback<ListEnterprises> {
            override fun onFailure(call: Call<ListEnterprises>, t: Throwable) {
                observablesFields.message.postValue(StringLiveData(ERROR_CONNECTION))
                observablesFields.loadingVisibility.postValue(EnabledChange())
            }

            override fun onResponse(call: Call<ListEnterprises>, response: Response<ListEnterprises>) {
                if (response.isSuccessful) {
                    enterpriseList.postValue(response.body()?.enterprises)
                }
                observablesFields.loadingVisibility.postValue(EnabledChange())
            }
        })

    }


    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br/"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"
        private const val ERROR_CONNECTION = "Falha na conexão"
        private const val ERROR_LOGIN: String = "Login Ínvalido"
    }

}
