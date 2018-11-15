package pedro.com.ioasystestekotlin.data.remote.sign

import kotlinx.coroutines.experimental.Job
import pedro.com.ioasystestekotlin.data.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.data.remote.model.UserApi

interface SignAuth {
    fun loginAccess(userApi: UserApi,
                   successLogin: (headers: HeaderApi) -> Unit,
                   errorLogin: (t: Throwable) -> Unit
    ): Job

}