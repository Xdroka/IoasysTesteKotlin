package pedro.com.ioasystestekotlin.data.remote.searchenterprises

import android.app.Application
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import pedro.com.ioasystestekotlin.data.ext.getHeader
import pedro.com.ioasystestekotlin.data.ext.headerMapper
import pedro.com.ioasystestekotlin.data.remote.model.ext.convertListOfEnterprises
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.domain.model.Enterprise

class SearchEnterprisesImpl(val app: Application,
                            private val service: WebService) : SearchEnterprises {

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit
    ): Job = launch {

        val response = service.searchEnterprise(
                nameSearchable = query,
                headers = app.getHeader().headerMapper()
        ).await()

        if (!response.isSuccessful) {
            errorSearch(Exception("HTTP: ${response.code()} - ${response.message()}"))
            return@launch
        }

        searchFound(
                response.body()?.enterprises?.convertListOfEnterprises()
                        ?: listOf(Enterprise())
        )

    }
}