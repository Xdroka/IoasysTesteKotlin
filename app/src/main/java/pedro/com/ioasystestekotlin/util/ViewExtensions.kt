package pedro.com.ioasystestekotlin.util

import android.support.v7.widget.SearchView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.data.remote.services.WebService

fun ImageView.downloadPhoto(photoUrl: String, enabledReDownload: Boolean = false) {
    if (enabledReDownload && photoUrl == "") {
        this.setImageResource(R.drawable.imageReport)
    } else {
        val imageFailure = R.drawable.download_icon

    Glide.with(context)
            .load(WebService.BASE_URL_PHOTO + photoUrl)
            .placeholder(R.drawable.loading)
            .error(imageFailure)
            .into(this)
    }
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

fun SearchView.dataBinding(onSubmit: () -> Unit,
                           onTextChanged: (newText: String) -> Unit){
    queryHint = context.getString(R.string.search_hint)

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(text: String?): Boolean {
            onSubmit()
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            onTextChanged(newText ?: "")
            return true
        }

    })
}
