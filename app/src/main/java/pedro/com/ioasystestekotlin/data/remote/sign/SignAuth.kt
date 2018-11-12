package pedro.com.ioasystestekotlin.data.remote.sign

import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import retrofit2.Response

interface SignAuth {
    fun loginAccess(userApi: UserApi,
                   successLogin: () -> Unit,
                   errorLogin: (t: Throwable) -> Unit
    ): DisposableObserver<Response<AuthRequest>>

}