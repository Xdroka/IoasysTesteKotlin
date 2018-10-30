package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
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

        binding.vm?.errorLoginEmail?.observe(this, Observer<EnabledChange> { t->
            val error = t?.enableChange
            if(error == true){
                binding.emailInputText.error = getString(R.string.error_invalid_email)
            }
            else{
                binding.emailInputText.error = null
            }
        })

        binding.vm?.errorLoginPassword?.observe(this, Observer<EnabledChange> { t->
            val error = t?.enableChange
            if(error == true){
                binding.passwordInputText.error = getString(R.string.error_invalid_password)
            }
            else{
                binding.passwordInputText.error = null
            }
        })

        binding.vm?.observables?.message?.observe(this, Observer<StringLiveData> { t ->
            val toastMessage = t?._text ?: ""
            if (toastMessage != "" ) {
                toast(toastMessage)
                binding.vm?.observables?.message?.postValue(StringLiveData())
            }
        })

        binding.vm?.observables?.changeActivity?.observe(this, Observer<EnabledChange> { t ->
            if (t?._enableChange == true){
                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                intent.putExtra("token", binding.vm?.api?.header?.access_token.toString())
                intent.putExtra("uid", binding.vm?.api?.header?.uid.toString())
                intent.putExtra("client", binding.vm?.api?.header?.client.toString())
                startActivity(intent)
            }
        })

        binding.vm?.observables?.loadingVisibility?.observe(this, Observer<EnabledChange>{ t->
            if(t?.enableChange == true){
                binding.progressBar.visibility = View.VISIBLE
            }
            else{
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.executePendingBindings()

    }

    private fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}
