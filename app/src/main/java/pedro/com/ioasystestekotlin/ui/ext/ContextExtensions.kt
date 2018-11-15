package pedro.com.ioasystestekotlin.ui.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()

inline fun <reified T : Activity> Activity.startActivity(mapKeys: Map<String, String>? = null) {
    val intent = Intent(this, T::class.java)
    mapKeys?.forEach {
        intent.putExtra(it.key, it.value)
    }
    startActivity(intent)
}

inline fun <reified T : Activity> Activity.startActivityAndFinish(mapKeys: Map<String, String>? = null) {
    startActivity<T>(mapKeys)
    finish()
}

