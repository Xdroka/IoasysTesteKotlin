package pedro.com.ioasystestekotlin.model.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.model.api.ApiConnection
import java.util.regex.Pattern

fun String.validatingPassword(): Boolean{
    return this.length >= 4
}

fun String.validatingEmail(): Boolean {
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
            .load(ApiConnection.BASE_URL_PHOTO + photoUrl)
            .placeholder(R.drawable.loading)
            .error(imageFailure)
            .into(this)
}

