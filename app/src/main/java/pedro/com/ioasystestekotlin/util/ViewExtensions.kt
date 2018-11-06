package pedro.com.ioasystestekotlin.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.model.api.WebService


fun ImageView.downloadPhoto(context: Context, photoUrl: String) {
    val imageFailure = if (photoUrl != "") {
        R.drawable.download
    } else {
        R.drawable.imageReport
    }

    Glide.with(context)
            .load(WebService.BASE_URL_PHOTO + photoUrl)
            .placeholder(R.drawable.loading)
            .error(imageFailure)
            .into(this)
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.turnIn(){
    this.isEnabled = true
}

fun View.turnOff(){
    this.isEnabled = false
}