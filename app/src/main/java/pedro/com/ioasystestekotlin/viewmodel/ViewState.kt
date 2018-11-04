package pedro.com.ioasystestekotlin.viewmodel

class ViewState<T>(
        val data: T?,
        val state: State,
        val throwable: Throwable? = null) {

    companion object {
        fun <T>success(t: T) = ViewState(t, State.SUCCESS, null)
        fun <T: Throwable>failure(t: T) = ViewState(null, State.FAILURE, t)
        fun loading() = ViewState(null, State.LOADING, null)
    }


}

enum class State{
    WAITING_DATA,
    GETTING_DATA,
    LOADING,
    SUCCESS,
    FAILURE
}