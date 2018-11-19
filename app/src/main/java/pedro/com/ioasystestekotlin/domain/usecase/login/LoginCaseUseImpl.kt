package pedro.com.ioasystestekotlin.domain.usecase.login

import kotlinx.coroutines.*
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.repository.login.Login

class LoginCaseUseImpl(private val authProvider: Login) : LoginCaseUse {
    private var job: Job = Job()

    override fun signIn(email: String, password: String,
                        onSuccess: () -> Unit,
                        onErrorLogin: (t: Throwable) -> Unit) {
        job = CoroutineScope(Dispatchers.IO)
                .launch {
                    authProvider.loginAccess(
                            UserApi(email = email,
                                    password = password
                            ),
                            onSuccess, onErrorLogin
                    )
                }
    }

    override fun cancelJob() {
        job.cancel()
    }

}