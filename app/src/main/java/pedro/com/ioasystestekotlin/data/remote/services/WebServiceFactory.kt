package pedro.com.ioasystestekotlin.data.remote.services

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

inline fun <reified T> createWebService(baseUrl: String): T =
        Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
                .create(T::class.java)

fun getBaseUrl() = "http://empresas.ioasys.com.br/api/v1/"
