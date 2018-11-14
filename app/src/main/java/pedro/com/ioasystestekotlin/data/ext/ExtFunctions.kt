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

fun Context.putSharedPreferences(keyToAccess: String,keys: Map<String ,String>, mode: Int = 0){
    val editor = getSharedPreferences(keyToAccess, mode).edit()
    keys.forEach{
        editor.putString(it.key, it.value)
    }
    editor.apply()
}

fun Context.getSharedPreferences(keyToAcess: String,
                                 keys: List<String>,
                                 mode: Int = 0): Map<String, String>{
    val sp = getSharedPreferences(keyToAcess, mode)
    lateinit var mapKeys: HashMap<String,String>
    keys.forEach{
        mapKeys[it] = sp.getString(it,"") ?: ""
    }
    return mapKeys
}


