package com.cataractaction.core.data

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}

data class SignInStateGoogle(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)