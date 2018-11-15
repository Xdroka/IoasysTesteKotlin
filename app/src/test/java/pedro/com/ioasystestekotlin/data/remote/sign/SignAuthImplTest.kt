package pedro.com.ioasystestekotlin.data.remote.sign

import android.app.Application
import org.junit.Assert.assertFalse
import org.junit.BeforeClass
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import pedro.com.ioasystestekotlin.data.ext.isValid
import pedro.com.ioasystestekotlin.data.ext.putSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.RANDOM_STRING
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.WebService

class SignAuthImplTest {
    @Mock private lateinit var app: Application
    @Mock private lateinit var service: WebService
    private lateinit var signAuth: SignAuth

    @BeforeClass
    fun setupMock() {
        MockitoAnnotations.initMocks(this)
        signAuth = SignAuthImpl(app, service)

    }

    @Test
    fun signCorrect() {
        var result = false
        doReturn(Unit).`when`(app).putSharedPreferences(RANDOM_STRING, mapOf())

        signAuth.loginAccess(
                UserApi(EMAIL_FIELD, PASSWORD_FIELD),
                successLogin = {
                    result = it.isValid()
                },
                errorLogin = {}
        )

        assert(result)
    }

    @Test
    fun signIncorrect() {
        var result = false
        doReturn(Unit).`when`(app).putSharedPreferences(RANDOM_STRING, mapOf())

        signAuth.loginAccess(
                UserApi(RANDOM_STRING, RANDOM_STRING),
                successLogin = {
                    result = it.isValid()
                },
                errorLogin = {}
        )

        assertFalse(result)
    }

    companion object {
        const val EMAIL_FIELD = "testeapple@ioasys.com.br"
        const val PASSWORD_FIELD = "12341234"
    }

}