package pedro.com.ioasystestekotlin.data.interactor

import android.app.Application
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.data.mapper.getHeader
import pedro.com.ioasystestekotlin.data.mapper.saveHeader
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import pedro.com.ioasystestekotlin.remote.data.RemoteData
import pedro.com.ioasystestekotlin.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.remote.model.UserApi
import pedro.com.ioasystestekotlin.remote.model.mapper.convertListOfEnterprises
import retrofit2.Response

class Repository(val app: Application) : RepositoryInterface {
    private val mRemoteData = RemoteData()

    override fun authentication(email: String, password: String,
                                successLogin: () -> Unit,
                                errorLogin: (t: Throwable) -> Unit
    ): DisposableObserver<Response<AuthRequest>> =

            mRemoteData.authentication(
                    UserApi(
                            email = email,
                            password = password
                    ))
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


    override fun searchEnterprises(queryName: String,
                                   searchFound: (List<Enterprise>) -> Unit,
                                   errorSearch: (t: Throwable) -> Unit
    ): DisposableObserver<Response<ListEnterprises>> =
            mRemoteData.searchEnterprises(queryName, app.getHeader())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<Response<ListEnterprises>>() {
                        override fun onComplete() {}

                        override fun onNext(response: Response<ListEnterprises>) {
                            if (!response.isSuccessful) {
                                errorSearch(
                                        Exception(
                                                "HTTP: ${response.code()} - ${response.message()}"
                                        )
                                )
                                return
                            }
                            searchFound(
                                    response.body()?.enterprises?.convertListOfEnterprises()
                                                                ?: listOf(Enterprise())
                            )

                        }

                        override fun onError(exception: Throwable) {
                            errorSearch(exception)
                        }
                    })

}

