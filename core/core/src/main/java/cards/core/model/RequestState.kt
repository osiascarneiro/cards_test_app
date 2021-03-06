package cards.core.model

sealed class RequestState<T> {
    data class Loading<T>(val loading: Boolean): RequestState<T>()
    data class Failure<T>(val error: Error): RequestState<T>()
    data class Success<T>(val result: T): RequestState<T>()
}