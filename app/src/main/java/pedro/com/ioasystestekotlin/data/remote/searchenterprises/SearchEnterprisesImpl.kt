package pedro.com.ioasystestekotlin.data.remote.searchenterprises

import android.app.Application
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.withTimeoutOrNull
import pedro.com.ioasystestekotlin.data.ext.getSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.ext.convertListOfEnterprises
import pedro.com.ioasystestekotlin.data.remote.services.WebService
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class SearchEnterprisesImpl(val app: Application,
                            private val service: WebService) : SearchEnterprises {

    override fun searchEnterprise(query: String,
                                  searchFound: (List<Enterprise>) -> Unit,
                                  errorSearch: (t: Throwable) -> Unit
    ): Job {
        return launch    {

            val response = service.searchEnterprise(
                    nameSearchable = query,
                    headers = app.getSharedPreferences(
                            keyToAccess = KEY_FIELD,
                            keys = listOf(TOKEN_FIELD, UID_FIELD, CLIENT_FIELD)
                    )
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

    companion object {
        const val TOKEN_FIELD = "access-token"
        const val CLIENT_FIELD = "client"
        const val UID_FIELD = "uid"
        const val KEY_FIELD = "headers"
    }
}