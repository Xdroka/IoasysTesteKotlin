package pedro.com.ioasystestekotlin.model.api

import android.arch.lifecycle.MutableLiveData
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.MessageError
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConnection {
    private var mRetrofit: Retrofit
    private var mService: WebService
    var header = MutableLiveData<HeaderApi?>().also { it.value = null }

    init {
        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        mService = mRetrofit.create(WebService::class.java)
    }

    fun auth(user: User, message: MutableLiveData<MessageError>) {
        val call = mService.authentification(user)

        call.enqueue(object : Callback<AuthRequest> {
            override fun onFailure(call: Call<AuthRequest>, t: Throwable) {
                message.value?._errorMessage = "Falha na conexão"
            }

            override fun onResponse(call: Call<AuthRequest>, response: Response<AuthRequest>) {
                if (response.isSuccessful) {
                    header.value = HeaderApi(
                                            response.headers().get("access-token"),
                                            response.headers().get("uid"),
                                            response.headers().get("client")
                    )
                } else {
                    message.value?._errorMessage = "Falha no Login"
                }
            }

        })
    }


    companion object {
        //        const val BASE_URL_PHOTO = "http://empresas.ioasys.com.br/"
        const val BASE_URL = "http://empresas.ioasys.com.br/api/v1/"
    }

}
