package pedro.com.ioasystestekotlin.model.data

import retrofit2.http.Headers

data class HeaderApi(var access_token: String,
                     var uid: String,
                     var client: String
)