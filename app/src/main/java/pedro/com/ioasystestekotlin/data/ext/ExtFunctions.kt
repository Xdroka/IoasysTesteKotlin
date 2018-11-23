package pedro.com.ioasystestekotlin.data.ext

import android.content.Context

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

fun Context.getStringByPreferences(keyToAccess: String, key: String = "") =
        getSharedPreferences(keyToAccess, listOf(key))[key] ?: ""

