package pedro.com.ioasystestekotlin.domain.interactor.sign

import io.reactivex.observers.DisposableObserver
import pedro.com.ioasystestekotlin.data.remote.sign.SignAuth
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import retrofit2.Response

class SignCaseUseImpl(private val authProvider: SignAuth) : SignCaseUse {

    lateinit var disposable: DisposableObserver<Response<AuthRequest>>

    override fun sign(email: String, password: String,
                      onSuccess: () -> Unit,
                      onErrorLogin: (t: Throwable) -> Unit) {
        disposable = authProvider.loginAccess(
                UserApi(email = email,
                        password = password
                ),
                onSuccess, onErrorLogin
        )
    }

    override fun disposeLogin() {
        disposable.dispose()
    }

}