package pedro.com.ioasystestekotlin.data.remote.repository.enterprise

import kotlinx.coroutines.Job
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface EnterpriseRepository  {
    suspend fun searchEnterprise(query: String,
                                 searchFound: (List<Enterprise>) -> Unit,
                                 errorSearch: (t: Throwable) -> Unit
    ): Job
}