package com.cataractaction.core.domain.model

data class AuthGoogleUserState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
    val isLoading: Boolean? = false
)

