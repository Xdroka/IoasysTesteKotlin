package pedro.com.ioasystestekotlin.util

import android.content.Context
import android.widget.Toast
import pedro.com.ioasystestekotlin.model.dataclass.HeaderApi

fun Context.saveHeader(header: HeaderApi){
    val sharedPreference = this.getSharedPreferences(
            "headers", 0
    )
    val editor = sharedPreference.edit()
    editor.putString("token", header.access_token )
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

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()
