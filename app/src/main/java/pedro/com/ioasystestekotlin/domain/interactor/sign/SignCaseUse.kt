package pedro.com.ioasystestekotlin.domain.interactor.sign

interface SignCaseUse {

    fun sign(email: String, password: String,
                     onSuccess: () -> Unit,
                     onErrorLogin: (t: Throwable) -> Unit){
    }

    fun cancelJob()
}