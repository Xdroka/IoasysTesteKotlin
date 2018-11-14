package pedro.com.ioasystestekotlin.domain.interactor.sign

import kotlinx.coroutines.experimental.Job
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.sign.SignAuth

class SignCaseUseImpl(private val authProvider: SignAuth) : SignCaseUse {
    private var job: Job = Job()

    override fun sign(email: String, password: String,
                              onSuccess: () -> Unit,
                              onErrorLogin: (t: Throwable) -> Unit) {
        job = authProvider.loginAccess(
                UserApi(email = email,
                        password = password
                ),
                onSuccess, onErrorLogin
        )
    }

    override fun cancelJob() {
        job.cancel()
    }

}