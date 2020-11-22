package cards.core.model

sealed class ApiResult<T> {
    data class Loading<T>(val loading: Boolean): ApiResult<T>()
    data class Failure<T>(val error: Error): ApiResult<T>()
    data class Success<T>(val result: T): ApiResult<T>()
}