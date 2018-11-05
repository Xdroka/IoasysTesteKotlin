package pedro.com.ioasystestekotlin.model.util

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.model.api.WebService.Companion.BASE_URL_PHOTO
import java.util.regex.Pattern

fun String.isPassword(): Boolean{
    return this.length >= 4
}

fun String.isEmail(): Boolean {
    val regex = "^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$"
    val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
    return pattern.matcher(this).find()
}

fun ImageView.downloadPhoto(context: Context, photoUrl: String, aboutScreen: Boolean = false) {
    val imageFailure = if (aboutScreen) {
        R.drawable.download_icon
    } else {
        R.drawable.imageReport
    }

    Glide.with(context)
            .load(BASE_URL_PHOTO + photoUrl)
            .placeholder(R.drawable.loading)
            .error(imageFailure)
            .into(this)
}


fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}