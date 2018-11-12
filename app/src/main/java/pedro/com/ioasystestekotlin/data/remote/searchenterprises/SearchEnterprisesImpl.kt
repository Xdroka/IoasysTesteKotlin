package pedro.com.ioasystestekotlin.data.remote.searchenterprises

import android.app.Application
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import pedro.com.ioasystestekotlin.data.ext.getHeader
import pedro.com.ioasystestekotlin.data.ext.headerMapper
import pedro.com.ioasystestekotlin.data.remote.model.ListEnterprises
import pedro.com.ioasystestekotlin.data.remote.model.ext.convertListOfEnterprises
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.domain.model.Enterprise
import retrofit2.Response

class SearchEnterprisesImpl(val app: Application,
                            private val service: WebService) : SearchEnterprises {

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit
    ): DisposableObserver<Response<ListEnterprises>> =
        service.searchEnterprise(query, app.getHeader().headerMapper())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toObservable()
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