package pedro.com.ioasystestekotlin.data.ext

import android.content.Context
import pedro.com.ioasystestekotlin.data.remote.model.HeaderApi
//
//fun HeaderApi.headerMapper(): HashMap<String, String> {
//    val headerHashMap = HashMap<String, String>()
//    headerHashMap["access-token"] = access_token
//    headerHashMap["client"] = client
//    headerHashMap["uid"] = uid
//    return headerHashMap
//}

fun Context.putSharedPreferences(keyToAccess: String, keys: Map<String, String>, mode: Int = 0): Boolean {
    val editor = getSharedPreferences(keyToAccess, mode).edit()
    keys.forEach {
        editor.putString(it.key, it.value)
    }
    editor.apply()
    return !keys.isEmpty()
}

fun Context.getSharedPreferences(keyToAccess: String,
                                 keys: List<String>,
                                 mode: Int = 0): Map<String, String> {
    val sp = getSharedPreferences(keyToAccess, mode)
    val mapKeys = HashMap<String, String>()
    keys.forEach {
        mapKeys[it] = sp.getString(it, "") ?: ""
    }
    return mapKeys
}


