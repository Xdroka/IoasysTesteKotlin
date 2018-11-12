package pedro.com.ioasystestekotlin.data.remote.sign

import android.app.Application
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.data.ext.saveHeader
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import retrofit2.Response

class SignAuthImpl(val app: Application,
                   private val service: WebService) : SignAuth {

    override fun loginAccess(userApi: UserApi,
                             successLogin: () -> Unit,
                             errorLogin: (t: Throwable) -> Unit
    ): DisposableObserver<Response<AuthRequest>> =
            service.authentication(userApi)
                    .toObservable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<Response<AuthRequest>>() {
                        override fun onNext(response: Response<AuthRequest>) {
                            if (response.body()?.success != true) {
                                errorLogin(Exception(LoginFieldState.loginInvalid()))
                                return
                            }

                            val headers = response.headers()
                            app.saveHeader(
                                    HeaderApi(
                                            access_token = headers["access-token"] ?: "",
                                            uid = headers["uid"] ?: "",
                                            client = headers["client"] ?: ""
                                    )
                            )
                            successLogin()

                        }

                        override fun onError(exception: Throwable) {
                            errorLogin(exception)
                        }

                        override fun onComplete() {}

                    })


}