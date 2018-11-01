package pedro.com.ioasystestekotlin.model.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.model.api.ApiConnection

class ImageUtil {
    companion object {
        fun downloadPhoto(context: Context, image: ImageView, photoUrl: String) {
            Glide.with(context)
                    .load(ApiConnection.BASE_URL_PHOTO + photoUrl)
                    .placeholder(R.drawable.loading)
                    .into(image)
        }
    }
}