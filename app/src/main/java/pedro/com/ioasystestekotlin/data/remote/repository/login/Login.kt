package pedro.com.ioasystestekotlin.data.remote.repository.login

import kotlinx.coroutines.Job
import pedro.com.ioasystestekotlin.data.remote.model.UserApi

interface Login {
    suspend fun loginAccess(userApi: UserApi,
                            successLogin: () -> Unit,
                            errorLogin: (t: Throwable) -> Unit
    ): Job

}