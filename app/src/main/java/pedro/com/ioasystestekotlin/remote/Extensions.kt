package pedro.com.ioasystestekotlin.remote

import pedro.com.ioasystestekotlin.remote.model.HeaderApi
import pedro.com.ioasystestekotlin.remote.data.RemoteData

fun RemoteData.headerMapper(headerApi: HeaderApi): HashMap<String, String> {
    val headerHashMap = HashMap<String, String>()
    headerHashMap["access-token"] = headerApi.access_token
    headerHashMap["client"] = headerApi.client
    headerHashMap["uid"] = headerApi.uid
    return headerHashMap
}