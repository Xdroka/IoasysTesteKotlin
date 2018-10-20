package pedro.com.ioasystestekotlin.view

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityLoginBinding
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.data.MessageError
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        binding.vm?.message?.observe(this, Observer<MessageError> { t ->
            if (t?.errorMessage != "") {
                toast(t?.errorMessage ?: "")
            }
        })
        binding.executePendingBindings()

    }

    fun Activity.toast(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }
}
