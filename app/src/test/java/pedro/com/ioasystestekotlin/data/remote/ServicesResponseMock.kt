package pedro.com.ioasystestekotlin.data.remote

data class ResponseTestMock<T>(val body: BodyTest<T> = BodyTest(),
                                val isSuccessful: Boolean = false
)


data class BodyTest<T>(var result: T? = null)
