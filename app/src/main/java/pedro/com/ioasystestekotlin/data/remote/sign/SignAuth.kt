package pedro.com.ioasystestekotlin.data.remote.sign

import kotlinx.coroutines.experimental.Job
import pedro.com.ioasystestekotlin.data.remote.model.UserApi

interface SignAuth {
    fun loginAccess(userApi: UserApi,
                   successLogin: () -> Unit,
                   errorLogin: (t: Throwable) -> Unit
    ): Job

}