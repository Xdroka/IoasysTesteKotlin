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
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel
import pedro.com.ioasystestekotlin.viewmodel.State

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = ViewModelProviders.of(this).get(LoginViewModel::class.java)
        creatingObservers(binding)

        binding.executePendingBindings()
    }

    private fun creatingObservers(binding: ActivityLoginBinding) {
        binding.vm?.state?.observe(this, Observer { viewState ->
            initializingLayout(binding)
            when (viewState?.state) {
                State.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }

                State.GETTING_DATA -> {
                    toast(viewState.data.toString())
                }

                State.SUCCESS -> {
                    callingHomeActivity(binding)
                }

                State.FAILURE -> {
                    if(viewState.data.equals("email")){
                        binding.emailInputText.error = getString(R.string.error_login)
                    }
                    else{
                        binding.passwordInputText.error = getString(R.string.error_invalid_password)
                    }
                }

                State.WAITING_DATA -> {
//                  do nothing
                }
            }
        })

   }

    private fun initializingLayout(binding: ActivityLoginBinding) {
        binding.progressBar.visibility = View.GONE
        binding.emailInputText.error = null
        binding.passwordInputText.error = null
    }

    private fun callingHomeActivity(binding: ActivityLoginBinding) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra("token", binding.vm!!.api.header.access_token)
        intent.putExtra("uid", binding.vm!!.api.header.uid)
        intent.putExtra("client", binding.vm!!.api.header.client)
        startActivity(intent)
    }

    private fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, duration).show()
    }
}
