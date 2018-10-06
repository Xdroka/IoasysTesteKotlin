package pedro.com.ioasystestekotlin.model

import android.databinding.BaseObservable

data class User(var email: String,
           var password: String
    ): BaseObservable()
