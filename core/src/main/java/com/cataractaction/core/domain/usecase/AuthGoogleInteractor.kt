package com.cataractaction.core.domain.usecase

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.cataractaction.core.domain.model.Resource
import com.cataractaction.core.domain.model.UserData
import com.cataractaction.core.domain.repository.IAuthGoogleRepository
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

class AuthGoogleInteractor(private val authGoogleRepository: IAuthGoogleRepository) :
    AuthGoogleUseCase {

    override suspend fun setupIntentSender(context: Context): IntentSender? =
        authGoogleRepository.setupIntentSender(context)

    override suspend fun googleWithIntent(intent: Intent): Flow<Resource<AuthResult>> =
        authGoogleRepository.googleWithIntent(intent)

    override suspend fun signUpWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>> =
        authGoogleRepository.signUpWithEmailAndPassword(email, password)

    override suspend fun signInWithEmailAndPassword(email: String, password: String): Flow<Resource<AuthResult>> =
        authGoogleRepository.signInWithEmailAndPassword(email, password)

    override suspend fun signOut() = authGoogleRepository.signOut()

    override fun getSignedInUser(): UserData? = authGoogleRepository.getSignedInUser()

}