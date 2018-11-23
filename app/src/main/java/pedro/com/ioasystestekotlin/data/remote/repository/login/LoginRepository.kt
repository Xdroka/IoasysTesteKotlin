package pedro.com.ioasystestekotlin.data.remote.repository.login

import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi

interface LoginRepository {
    suspend fun loginAccess(userApi: UserApi): ResultRequest<Map<String,String>>
}
