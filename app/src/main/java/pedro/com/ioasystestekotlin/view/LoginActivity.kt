package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityLoginBinding
import pedro.com.ioasystestekotlin.model.data.EnabledChange
import pedro.com.ioasystestekotlin.model.data.StringLiveData
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.vm?.message?.observe(this, Observer<StringLiveData> { t ->
            val errorMessage = t?._text ?: ""
            if (errorMessage != "" ) {
                toast(errorMessage)
                binding.vm?.message?.postValue(StringLiveData())
            }
        })

        binding.vm?.changeActivity?.observe(this, Observer<EnabledChange> { t ->
            if (t?._enableChange == true){
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                intent.putExtra("token", binding.vm?.api?.header?.access_token)
                intent.putExtra("uid", binding.vm?.api?.header?.uid)
                intent.putExtra("client", binding.vm?.api?.header?.client)
                startActivity(intent)
            }
        })

        binding.executePendingBindings()

    }

    private fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}
