package pedro.com.ioasystestekotlin.domain.usecase.login

import android.app.Application
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.data.cache.room.repo.HeaderRoom
import pedro.com.ioasystestekotlin.data.remote.repository.login.LoginRepository
import pedro.com.ioasystestekotlin.domain.usecase.mockHeaderMap
import pedro.com.ioasystestekotlin.domain.usecase.mockResultRequest

class LoginUseCaseImplTest {
    @Mock
    private lateinit var authProvider: LoginRepository
    @Mock
    private lateinit var headerRoom: HeaderRoom
    @Mock

    private lateinit var resultRequest: ResultRequest<Map<String, String>>

    private lateinit var loginUseCase: LoginUseCaseImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        loginUseCase = LoginUseCaseImpl(authProvider, headerRoom)
    }

    @Test
    fun loginValidTest() {
        var result = false
        `when`(headerRoom.insertHeader(any())).thenReturn(true)
        mockResultRequest(data = mockHeaderMap())

        GlobalScope.launch {
            `when`(authProvider.loginAccess(any())).thenReturn(resultRequest)
        }

        loginUseCase.signIn(email = any(), password = any(),
                onSuccess = {
                    result = true
                },
                onErrorLogin = {
                    result = false
                }
        )
        assertTrue(result)
    }


}