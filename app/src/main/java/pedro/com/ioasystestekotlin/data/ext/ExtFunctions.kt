package pedro.com.ioasystestekotlin.data.ext

import android.content.Context
import pedro.com.ioasystestekotlin.data.remote.model.HeaderApi


fun Context.saveHeader(header: HeaderApi) {
    val sharedPreference = this.getSharedPreferences(
            "headers", 0
    )
    val editor = sharedPreference.edit()
    editor.putString("token", header.access_token)
    editor.putString("uid", header.uid)
    editor.putString("client", header.client)
    editor.apply()
}

fun Context.getHeader(): HeaderApi {
    val sharedPreference = this.getSharedPreferences(
            "headers", 0
    )
    return HeaderApi(
            sharedPreference.getString("token", "") ?: "",
            sharedPreference.getString("uid", "") ?: "",
            sharedPreference.getString("client", "") ?: ""
    )
}

fun HeaderApi.headerMapper(): HashMap<String, String> {
    val headerHashMap = HashMap<String, String>()
    headerHashMap["access-token"] = access_token
    headerHashMap["client"] = client
    headerHashMap["uid"] = uid
    return headerHashMap
}