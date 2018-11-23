package pedro.com.ioasystestekotlin.data.remote.repository.login

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.UserWebService
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState.Companion.loginInvalid
import retrofit2.HttpException
import retrofit2.Response

class LoginRepositoryImplTest {

    @Mock
    lateinit var service: UserWebService
    @Mock
    lateinit var deferred: Deferred<Response<AuthRequest>>
    @Mock
    lateinit var response: Response<AuthRequest>

    private val userApi = UserApi()
    private lateinit var loginRepository: LoginRepositoryImpl

    @Before
    fun setupMocks() {
        MockitoAnnotations.initMocks(this)
        loginRepository = LoginRepositoryImpl(service)
    }

    @Test
    fun testSignInFailureEmail() {
        var result: ResultRequest<Map<String,String>>? = null
        GlobalScope.launch {
            `when`(service.authentication(userApi)).thenReturn(deferred)
            `when`(deferred.await()).thenThrow(HttpException(response))
            `when`(response.errorBody()).thenThrow(HttpException(response))
            `when`(response.message()).thenReturn("Erro")
            Mockito.verify(service)

            loginRepository.loginAccess(userApi).apply {
                result = this
            }
        }
        assertTrue(result?.throwable == Exception(loginInvalid()))

    }

}