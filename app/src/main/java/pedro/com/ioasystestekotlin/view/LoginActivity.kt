package pedro.com.ioasystestekotlin.view

import android.app.Activity
import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityLoginBinding
import pedro.com.ioasystestekotlin.model.User
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)

        var binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = LoginViewModel()

        binding.vm?.user?.postValue(User("welcome","8888"))
    }


    fun Activity.toast(message: String, duration: Int = Toast.LENGTH_LONG){
        Toast.makeText(this, message, duration);
    }
}