package pedro.com.ioasystestekotlin.data.remote.repository.login

import android.app.Application
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.MockitoAnnotations
import pedro.com.ioasystestekotlin.data.ext.putSharedPreferences
import pedro.com.ioasystestekotlin.data.remote.model.AuthRequest
import pedro.com.ioasystestekotlin.data.remote.model.UserApi
import pedro.com.ioasystestekotlin.data.remote.services.EnterprisesWebService
import retrofit2.Response


class SignAuthImplTest {
//    @Mock
//    private lateinit var app: Application
//    @Mock
//    private lateinit var serviceEnterprises: EnterprisesWebService
//    private lateinit var response: Response<AuthRequest>
//    private lateinit var loginRepository: LoginRepository
//    private val userApi = UserApi()
//
//    @Before
//    fun setupMock() {
//        MockitoAnnotations.initMocks(this)
//        loginRepository = LoginRepositoryImpl(app, serviceEnterprises)
//    }
//
//    @Test
//    fun signCorrect() {
//        var result = false
//        initResponse(true, true)
////        launch {
////            doReturn(response).`when`(serviceEnterprises).authentication(userApi).await()
////        }
////        loginRepository.loginAccess(
////                userApi = userApi,
////                successLogin = {
////                    print("GG")
////                    result = true
////                },
////                errorLogin = {
////                    result = false
////                    print(it.message)
////                }
////        )
//
//        assertTrue(result)
//    }
//
//    @Test
//    fun signIncorrect() {
//        var result = false
//        doReturn(Unit).`when`(app).putSharedPreferences("headers", mapOf())
//        initResponse(true, false)
//////        launch {
//////            doReturn(response).`when`(serviceEnterprises).authentication(userApi).await()
//////        }
////
////        loginRepository.loginAccess(
////                userApi = userApi,
////                successLogin = {
////                    result = true
////                },
////                errorLogin = {
////                    print(it.message)
////                }
////        )
//
//        assertFalse(result)
//    }
//
//    @Test
//    fun failConnection() {
//        var result = true
//        initResponse(isSuccessful = false, code = 408)
////        launch {
////            doThrow(HttpException(response)).`when`(serviceEnterprises).authentication(any()).await()
////        }
//
////        loginRepository.loginAccess(
////                userApi = userApi,
////                successLogin = {},
////                errorLogin = {
////                    print(it.message)
////                    result = false
////                }
////        )
//
//        assertFalse(result)
//    }
//
//
//    private fun initResponse(isSuccessful: Boolean, success: Boolean = true, code: Int = 200) {
//        response = if (isSuccessful) {
//            Response.success(
//                    AuthRequest(success),
//                    Headers.of(
//                            mutableMapOf(
//                                    Pair(RANDOM_STRING, RANDOM_STRING)
//                            )
//                    )
//            )
//        } else {
//            Response.error(code,
//                    ResponseBody.create(MediaType.parse(RANDOM_STRING),
//                            RANDOM_STRING
//                    )
//            )
//        }
//    }
//
//    companion object {
//        const val RANDOM_STRING = "RANDOM"
//    }
}