package pedro.com.ioasystestekotlin.data.remote.repository.enterprise

import android.app.Application
import kotlinx.coroutines.*
import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.data.cache.entities.HeaderAccess
import pedro.com.ioasystestekotlin.data.ext.getSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.ext.convertListOfEnterprises
import pedro.com.ioasystestekotlin.data.remote.services.EnterprisesWebService
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

class EnterpriseRepositoryImpl(private val serviceEnterprises: EnterprisesWebService) : EnterpriseRepository {

    override suspend fun searchEnterprise(query: String,
                                          mapHeader: Map<String, String>): ResultRequest<List<Enterprise>> {
        val response = serviceEnterprises.searchEnterprise(
                nameSearchable = query,
                headers = mapHeader
        ).await()

        if (!response.isSuccessful) {
            return ResultRequest.error(Exception("HTTP: ${response.code()} - ${response.message()}"))
        }

        return ResultRequest.success(
                response.body()?.enterprises?.convertListOfEnterprises()
                        ?: listOf(Enterprise())
        )
    }

    companion object {
        const val TOKEN_FIELD = "access-token"
        const val CLIENT_FIELD = "client"
        const val UID_FIELD = "uid"
        const val KEY_FIELD = "headers"
    }
}