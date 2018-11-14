package pedro.com.ioasystestekotlin.data.remote.searchenterprises

import kotlinx.coroutines.experimental.Job
import pedro.com.ioasystestekotlin.domain.model.Enterprise

interface SearchEnterprises  {
    fun searchEnterprise(query: String,
                         searchFound: (List<Enterprise>) -> Unit,
                         errorSearch: (t: Throwable) -> Unit
    ): Job
}