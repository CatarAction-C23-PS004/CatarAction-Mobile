package com.cataractaction.core.domain.model

data class AuthGoogleUserData(
    val userId: String,
    val username: String?,
    val email: String?,
    val profilePictureUrl: String?
)
