package pedro.com.ioasystestekotlin.viewmodel

class ViewState<T>(
        val data: T?,
        val state: State,
        val throwable: Throwable? = null) {

    companion object {
        fun <T> success(): ViewState<T> = ViewState(null, State.SUCCESS)

        fun <T> success(t: T): ViewState<T> = ViewState(t, State.SUCCESS)

        fun <T> failure(t: Throwable) = ViewState<T>(null, State.FAILURE, t)

        fun <T> loading() = ViewState<T>(null, State.LOADING)

        fun <T> gettingData(t: T) = ViewState(t, State.GETTING_DATA)

        fun <T> initializing() = ViewState<T>(null, State.WAITING_DATA)
    }
}

enum class State{
    WAITING_DATA,
    GETTING_DATA,
    LOADING,
    SUCCESS,
    FAILURE
}