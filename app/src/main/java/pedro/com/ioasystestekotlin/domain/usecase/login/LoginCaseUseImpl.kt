package pedro.com.ioasystestekotlin.domain.usecase.login

import android.app.Application
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pedro.com.ioasystestekotlin.data.cache.convertToHeader
import pedro.com.ioasystestekotlin.data.cache.room.repo.HeaderRoom
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.repository.login.LoginRepository
import kotlin.coroutines.CoroutineContext

class LoginCaseUseImpl(private val authProvider: LoginRepository,
                       private val headerRoom: HeaderRoom) : LoginCaseUse, CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    override fun signIn(email: String, password: String,
                        onSuccess: () -> Unit,
                        onErrorLogin: (t: Throwable) -> Unit) {
        job = launch(coroutineContext) {

            val result = authProvider.loginAccess(
                    UserApi(email = email,
                            password = password
                    )
            )
            if(result.throwable != null) {
                onErrorLogin(result.throwable)
                return@launch
            }

            result.data?.apply {
                headerRoom.insertHeader(convertToHeader())
                Log.d("LOGIN_CASE", toString())
            }
            onSuccess()

        }
    }

    override fun cancelJob() {
        job.cancel()
    }

}