package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityLoginBinding
import pedro.com.ioasystestekotlin.model.data.HeaderApi
import pedro.com.ioasystestekotlin.model.util.toast
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel
import pedro.com.ioasystestekotlin.viewmodel.Status

class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil
                .setContentView(this, R.layout.activity_login)
        binding.vm = viewModel
        lifecycle.addObserver(viewModel)
        creatingObservers()
        binding.executePendingBindings()
    }

    private fun creatingObservers() {
        viewModel.getState().observe(this, Observer { viewState ->
            initializingLayout()

            when (viewState?.status) {
                Status.SUCCESS -> {
                    viewState.data?.let {
                        startHomeActivity(it)
                    }
                }

                Status.FAILURE -> {
                    viewState.throwable?.let {
                        toast(it.message ?: "Erro desconhecido")
                    }
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    sigin_button.isEnabled = false
                }

                else -> {

                }

            }
        })

    }

    private fun initializingLayout() {
        progressBar.visibility = View.GONE
        emailInputLayout.error = null
        passwordInputLayout.error = null
    }

    private fun startHomeActivity(header: HeaderApi) {
        val intent = Intent(this@LoginActivity, HomeActivity::class.java)
        intent.putExtra("token", header.access_token)
        intent.putExtra("uid", header.uid)
        intent.putExtra("client", header.client)
        startActivity(intent)
        finish()

    }

}
