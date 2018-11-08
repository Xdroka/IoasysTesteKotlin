package pedro.com.ioasystestekotlin.model.interactor

import android.app.Application
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.model.api.RemoteData
import pedro.com.ioasystestekotlin.model.dataclass.*
import pedro.com.ioasystestekotlin.util.getHeader
import pedro.com.ioasystestekotlin.util.saveHeader
import retrofit2.Response

class Repository(val app: Application) : RepositoryInterface {
    private val mRemoteData = RemoteData()

    override fun authentication(user: User,
                                successLogin: () -> Unit,
                                errorLogin: (t: Throwable) -> Unit
    ): DisposableObserver<Response<AuthRequest>> =

            mRemoteData.authentication(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableObserver<Response<AuthRequest>>() {
                        override fun onNext(response: Response<AuthRequest>) {
                            if (response.body()?.success != true) {
                                errorLogin(Exception("loginInvalid"))
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

                            response.body()?.enterprises?.let { list ->
                                searchFound(list)
                                return
                            }
                            searchFound(listOf(Enterprise()))
                        }

                        override fun onError(exception: Throwable) {
                            errorSearch(exception)
                        }
                    })

}

