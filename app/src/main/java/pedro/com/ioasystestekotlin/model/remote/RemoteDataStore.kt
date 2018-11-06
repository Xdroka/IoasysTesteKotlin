package pedro.com.ioasystestekotlin.model.remote

import io.reactivex.Observable
import pedro.com.ioasystestekotlin.model.data.AuthRequest
import pedro.com.ioasystestekotlin.model.data.Enterprise
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import retrofit2.Response

interface RemoteDataStore {

    fun login(email: String, password: String): Observable<Response<AuthRequest>>

    fun searchEnterprises(query: String, headerApi: HeaderApi): Observable<List<Enterprise>>

}