package pedro.com.ioasystestekotlin.viewmodel

class ViewState<T>(
        val data: T?,
        val state: State
)

enum class State{
    WAITING_DATA,
    GETTING_DATA,
    LOADING,
    SUCCESS,
    FAILURE
}