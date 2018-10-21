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

    fun auth(user: User, message: MutableLiveData<ErrorMessage>,
             changeActivity: MutableLiveData<EnabledChange>, buttonEnabled: MutableLiveData<EnabledChange>) {
        val call = mService.authentification(user)

        call.enqueue(object : Callback<AuthRequest> {
            override fun onFailure(call: Call<AuthRequest>, t: Throwable) {
                message.postValue(ErrorMessage("Falha na conexão"))
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
                } else {
                    message.postValue(ErrorMessage("Falha no Login"))
                }
                message.postValue(ErrorMessage(header?.uid.toString()))
                buttonEnabled.postValue(EnabledChange(true))
            }

        })
    }


    companion object {
        //        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br/"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"
    }

}
