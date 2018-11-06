package pedro.com.ioasystestekotlin.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityLoginBinding
import pedro.com.ioasystestekotlin.model.dataclass.HeaderApi
import pedro.com.ioasystestekotlin.util.*
import pedro.com.ioasystestekotlin.viewmodel.LoginViewModel
import pedro.com.ioasystestekotlin.viewmodel.State

class LoginActivity : AppCompatActivity() {
//    private val mViewModel: LoginViewModel by lazy {
//        ViewModelProviders.of(this).get(LoginViewModel::class.java)
//    }
    private val mViewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.vm = mViewModel
        creatingObservers()

        binding.executePendingBindings()
    }

    private fun creatingObservers() {
        mViewModel.getState().observe(this, Observer { viewState ->
            initializingLayout()

            Log.d("FEED",
                    "${viewState?.state} -  ${viewState?.data} " +
                            "- ${viewState?.throwable?.message}"
            )

            when (viewState?.state) {
                State.LOADING -> {
                    progressBar.show()
                    siginButton.turnOff()
                }

                State.SUCCESS -> {
                    viewState.data?.let { header ->
                        callingHomeActivity(header)
                    }
                }

                State.FAILURE -> {
                    val errorMessage = viewState.throwable?.message

                    when (errorMessage) {
                        "emailInvalid" -> {
                            invalidEmail()
                        }

                        "passwordInvalid" -> {
                            invalidPassword()
                        }

                        "bothInvalid" -> {
                            invalidEmail()
                            invalidPassword()
                        }

                        "loginInvalid" -> {
                            toast(getString(R.string.invalid_login))
                        }

                        else -> {
                            toast(getString(R.string.error_connectior))
                        }
                    }
                }

                else -> {
//                    do nothing
                }
            }

        })

    }

    private fun invalidPassword() {
        passwordInputText.error = getString(R.string.error_invalid_password)
    }

    private fun invalidEmail() {
        emailInputText.error = getString(R.string.error_invalid_email)
    }

    private fun initializingLayout() {
        progressBar.hide()
        siginButton.turnIn()
        emailInputText.error = null
        passwordInputText.error = null
    }

    private fun callingHomeActivity(header: HeaderApi) {
        saveHeader(header)
        startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
        finish()
    }


}


