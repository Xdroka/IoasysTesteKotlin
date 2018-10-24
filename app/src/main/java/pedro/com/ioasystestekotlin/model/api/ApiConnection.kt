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
    var header: HeaderApi? = null

    init {
        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mService = mRetrofit.create(WebService::class.java)
    }

    fun auth(user: User, message: MutableLiveData<StringLiveData>,
             changeActivity: MutableLiveData<EnabledChange>, buttonEnabled: MutableLiveData<EnabledChange>) {
        val call = mService.authentification(user)

        call.enqueue(object : Callback<AuthRequest> {
            override fun onFailure(call: Call<AuthRequest>, t: Throwable) {
                message.postValue(StringLiveData("Falha na conexão"))
                buttonEnabled.postValue(EnabledChange(true))
            }

            override fun onResponse(call: Call<AuthRequest>, response: Response<AuthRequest>) {
                if (response.isSuccessful) {
                    header = HeaderApi(
                            response.headers().get("access-token"),
                            response.headers().get("uid"),
                            response.headers().get("client")
                    )
                    changeActivity.postValue(EnabledChange(true))
                    message.postValue(StringLiveData(header?.uid.toString())) //retirar essa linha depois de testes
                } else {
                    message.postValue(StringLiveData("Login Ínvalido"))
                }
                buttonEnabled.postValue(EnabledChange(true))
            }

        })
    }

    fun searchEnterprises(enterpriseName: String, messageToast: MutableLiveData<StringLiveData>) {
        if (header == null) return
        val call = mService.findEnterprises(enterpriseName, header!!)

        call.enqueue(object : Callback<List<Enterprise>> {
            override fun onFailure(call: Call<List<Enterprise>>, t: Throwable) {
                messageToast.postValue(StringLiveData("Falha na conexão"))
            }

            override fun onResponse(call: Call<List<Enterprise>>, response: Response<List<Enterprise>>) {
                if (!response.isSuccessful) return

                messageToast.postValue(StringLiveData(response.body()?.get(0)?.enterprise_type_name ?: "fail"))

            }

        })
    }


    companion object {
        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br/"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"
    }

}
