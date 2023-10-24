package com.complexsoft.common.utils.navigation.events

sealed class UIEvent<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : UIEvent<T>()
    class Success<T>(data: T?) : UIEvent<T>(data = data)
    class Error<T>(message: String?) : UIEvent<T>(message = message)
}
