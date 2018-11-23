package pedro.com.ioasystestekotlin.data.remote.repository.enterprise

import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface EnterpriseRepository  {
    suspend fun searchEnterprise(query: String, mapHeader: Map<String, String>): ResultRequest<List<Enterprise>>
}