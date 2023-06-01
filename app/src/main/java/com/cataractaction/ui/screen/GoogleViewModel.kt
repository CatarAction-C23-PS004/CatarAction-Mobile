package com.cataractaction.ui.screen

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import com.cataractaction.core.data.AuthGoogleRepository
import com.cataractaction.core.data.SignInStateGoogle
import com.cataractaction.core.domain.model.SignInResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GoogleViewModel @Inject constructor(private val authGoogleRepository: AuthGoogleRepository) :
    ViewModel() {

    private val _state = MutableStateFlow(SignInStateGoogle())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _state.update {
            it.copy(
                isSignInSuccessful = result.data != null,
                signInError = result.errorMessage
            )
        }
    }

    fun resetState() {
        _state.update { SignInStateGoogle() }
    }

    suspend fun signInWithIntent(intent: Intent): SignInResult =
        authGoogleRepository.signInWithIntent(intent)

    suspend fun getGoogleAuth(context: Context): IntentSender? =
        authGoogleRepository.getGoogleAuth(context)
}