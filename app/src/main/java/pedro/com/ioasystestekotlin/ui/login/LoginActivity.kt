package pedro.com.ioasystestekotlin.ui.login

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.viewmodel.ext.android.viewModel
import pedro.com.ioasystestekotlin.R
import pedro.com.ioasystestekotlin.databinding.ActivityLoginBinding
import pedro.com.ioasystestekotlin.presentation.State
import pedro.com.ioasystestekotlin.presentation.login.LoginFieldState
import pedro.com.ioasystestekotlin.presentation.login.LoginViewModel
import pedro.com.ioasystestekotlin.ui.ext.*
import pedro.com.ioasystestekotlin.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {
    private val mViewModel: LoginViewModel by viewModel()

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

            when (viewState?.state) {
                State.LOADING -> {
                    progressBar.show()
                    signButton.turnOff()
                    emailInputText.turnOff()
                    passwordInputText.turnOff()
                }

                State.SUCCESS -> {
                    startActivityAndFinish<HomeActivity>()
                }

                State.FAILURE -> {
                    val errorMessage = viewState.throwable?.message

                    when (errorMessage) {
                        LoginFieldState.emailError() -> {
                            invalidateEmail()
                        }

                        LoginFieldState.passwordError() -> {
                            invalidatePassword()
                        }

                        LoginFieldState.bothError() -> {
                            invalidateEmail()
                            invalidatePassword()
                        }

                        LoginFieldState.loginInvalid() -> {
                            toast(getString(R.string.invalid_login), Toast.LENGTH_LONG)
                        }

                        else -> {
                            toast(getString(R.string.error_connectior), Toast.LENGTH_LONG)
                        }
                    }
                }

                else -> {
//                    do nothing
                }
            }

        })

    }

    private fun invalidatePassword() {
        passwordInputText.error = getString(R.string.error_invalid_password)
    }

    private fun invalidateEmail() {
        emailInputText.error = getString(R.string.error_invalid_email)
    }

    private fun initializingLayout() {
        progressBar.hide()
        emailInputText.turnIn()
        passwordInputText.turnIn()
        signButton.turnIn()
        emailInputText.error = null
        passwordInputText.error = null
    }


}


