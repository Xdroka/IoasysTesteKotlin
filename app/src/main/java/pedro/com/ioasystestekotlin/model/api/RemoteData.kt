package pedro.com.ioasystestekotlin.model.api

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.ListEnterprises
import pedro.com.ioasystestekotlin.model.data.User
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteData : RemoteDataInterface {
    var mservice: WebService = createWebService()

    override fun authentication(user: User)
            : Observable<Response<AuthRequest>> = mservice.authentication(user).toObservable()

    override fun searchEnterprises(queryName: String,
                                   headerApi: HeaderApi): Observable<Response<ListEnterprises>> =
            mservice.searchEnterprise(queryName,
                                      hashMapOf(
                                                "access-tokne" to headerApi.access_token,
                                                "uid" to headerApi.uid,
                                                "client" to headerApi.client
                                                )
            ).toObservable()


}

inline fun <reified T> createWebService(): T = Retrofit
        .Builder()
        .baseUrl(WebService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(T::class.java)
