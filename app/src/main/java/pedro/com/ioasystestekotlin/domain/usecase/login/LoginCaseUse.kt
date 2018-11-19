package pedro.com.ioasystestekotlin.domain.usecase.login

interface LoginCaseUse {

    fun signIn(email: String, password: String,
               onSuccess: () -> Unit,
               onErrorLogin: (t: Throwable) -> Unit){
    }

    fun cancelJob()
}