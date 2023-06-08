package com.cataractaction.core.domain.usecase

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.cataractaction.core.domain.model.Resource
import com.cataractaction.core.domain.model.UserData
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface AuthGoogleUseCase {
    suspend fun setupIntentSender(context: Context): IntentSender?

    suspend fun googleWithIntent(intent: Intent): Flow<Resource<AuthResult>>

    suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>>

    suspend fun signOut()

    fun getSignedInUser(): UserData?
}