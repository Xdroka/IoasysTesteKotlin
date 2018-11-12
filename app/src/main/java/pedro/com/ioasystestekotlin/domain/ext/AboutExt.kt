package pedro.com.ioasystestekotlin.domain.ext

import android.content.Context
import pedro.com.ioasystestekotlin.domain.model.Enterprise

fun Context.putEnterprise(enterprise: Enterprise) {
    val editor = getSharedPreferences("enterprise", 0).edit()
    editor.putString("description", enterprise.description)
    editor.putString("enterpriseName", enterprise.name)
    editor.putString("photoUrl", enterprise.photo)
    editor.apply()
}

fun Context.getEnterprise(): Enterprise {
    val sp = getSharedPreferences("enterprise", 0)
    return Enterprise(
            name = sp.getString("enterpriseName","") ?: "",
            description = sp.getString("description", "")?:"",
            photo = sp.getString("photoUrl","")?:""
    )
}