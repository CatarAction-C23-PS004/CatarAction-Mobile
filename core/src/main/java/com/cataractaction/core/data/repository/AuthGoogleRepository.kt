package com.cataractaction.core.data.repository

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.cataractaction.core.R
import com.cataractaction.core.data.Resource
import com.cataractaction.core.domain.model.AuthGoogleUserData
import com.cataractaction.core.domain.repository.IAuthGoogleRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject


class AuthGoogleRepository @Inject constructor(
    private val oneTapClient: SignInClient
) : IAuthGoogleRepository {

    private val auth = Firebase.auth

    override suspend fun setupIntentSender(context: Context): IntentSender? {
        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest(context)
            ).await()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }

    override suspend fun googleWithIntent(intent: Intent):  Flow<Resource<AuthResult>> = flow<Resource<AuthResult>> {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)

        emit(Resource.Loading)
        val result = auth.signInWithCredential(googleCredential).await()
        emit(Resource.Success(result))
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.message.toString()))
    }


    override suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading)
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        emit(Resource.Success(result))
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.message.toString()))
    }


    override suspend fun signInWithEmailAndPassword(
        email: String,
        password: String
    ): Flow<Resource<AuthResult>> = flow {
        emit(Resource.Loading)
        val result = auth.signInWithEmailAndPassword(email, password).await()
        emit(Resource.Success(result))
    }.catch {
        it.printStackTrace()
        emit(Resource.Error(it.message.toString()))
    }

    override suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    override fun getSignedInUser(): AuthGoogleUserData? = auth.currentUser?.run {
        AuthGoogleUserData(
            userId = uid,
            username = displayName,
            email = email,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    override fun buildSignInRequest(context: Context): BeginSignInRequest {
        return BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.Builder().setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id)).build()
        ).setAutoSelectEnabled(true).build()
    }

}