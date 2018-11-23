package pedro.com.ioasystestekotlin.data.remote.repository.login

import pedro.com.ioasystestekotlin.data.Result
import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.presentation.model.Enterprise

interface LoginRepository {
    suspend fun loginAccess(userApi: UserApi): ResultRequest<Map<String,String>>
}
