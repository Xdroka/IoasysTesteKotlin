package pedro.com.ioasystestekotlin.presentation.login

data class LoginFieldState(val state: WarningSignal){
    private var message: String = state.name

    companion object {
        fun emailError() = LoginFieldState(WarningSignal.EMAIL_WARNING).message

        fun passwordError() = LoginFieldState(WarningSignal.PASSWORD_WARNING).message

        fun bothError() = LoginFieldState(WarningSignal.BOTH_WARNING).message

        fun loginInvalid() = LoginFieldState(WarningSignal.LOGIN_INVALID).message
    }
}

enum class WarningSignal {
    EMAIL_WARNING,
    PASSWORD_WARNING,
    BOTH_WARNING,
    LOGIN_INVALID
}