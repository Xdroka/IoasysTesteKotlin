package pedro.com.ioasystestekotlin.viewmodel

class ViewState<T>(
        val status: Status,
        val data: T?,
        val throwable: Throwable? = null) {

    companion object {

        fun <T> success(data: T? = null): ViewState<T> {
            return ViewState(Status.SUCCESS, data, null)
        }

        fun <T> error(error: Throwable?): ViewState<T> {
            return ViewState(Status.FAILURE, null, error)
        }

        fun <T> loading(): ViewState<T> {
            return ViewState(Status.LOADING, null, null)
        }

    }

}

enum class Status{
    WAITING_DATA,
    GETTING_DATA,
    LOADING,
    SUCCESS,
    FAILURE
}