package pedro.com.ioasystestekotlin.domain.usecase.login

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import pedro.com.ioasystestekotlin.data.cache.convertToHeader
import pedro.com.ioasystestekotlin.data.cache.room.repo.HeaderRoom
import pedro.com.ioasystestekotlin.data.ext.putSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.repository.login.LoginRepository
import kotlin.coroutines.CoroutineContext

class LoginCaseUseImpl(private val authProvider: LoginRepository,
                       private val headerRoom: HeaderRoom,
                       private val app: Application) : LoginCaseUse, CoroutineScope {
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
            if (result.throwable != null || result.data == null) {
                onErrorLogin(result.throwable as Throwable)
                return@launch
            }

            app.putSharedPreferences(keyToAccess = "uid",
                                     keys = mapOf("uid" to email)
            )
            headerRoom.insertHeader(result.data.convertToHeader())
            onSuccess()

        }
    }

    override fun cancelJob() {
        job.cancel()
    }

}