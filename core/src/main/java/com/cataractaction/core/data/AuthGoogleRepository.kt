package com.cataractaction.core.data

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import com.cataractaction.core.R
import com.cataractaction.core.domain.model.SignInResult
import com.cataractaction.core.domain.model.UserData
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import java.util.concurrent.CancellationException
import javax.inject.Inject


class AuthGoogleRepository @Inject constructor(
    private val oneTapClient: SignInClient
) {

    private val auth = Firebase.auth

    suspend fun getGoogleAuth(context: Context): IntentSender? {
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

    suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        return try {
            val user = auth.signInWithCredential(googleCredential).await().user
            SignInResult(data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePictureUrl = photoUrl?.toString()
                )
            }, errorMessage = null)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            SignInResult(data = null, errorMessage = e.message)
        }
    }

    suspend fun signOut() {
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser(): UserData? = auth.currentUser?.run {
        UserData(
            userId = uid,
            username = displayName,
            email = email,
            profilePictureUrl = photoUrl?.toString()
        )
    }

    private fun buildSignInRequest(context: Context): BeginSignInRequest {
        return BeginSignInRequest.Builder().setGoogleIdTokenRequestOptions(
            GoogleIdTokenRequestOptions.Builder().setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(context.getString(R.string.default_web_client_id)).build()
        ).setAutoSelectEnabled(true).build()
    }

}