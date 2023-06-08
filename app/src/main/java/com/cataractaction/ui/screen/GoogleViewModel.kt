package com.cataractaction.ui.screen

import android.content.Context
import android.content.Intent
import android.content.IntentSender
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cataractaction.core.domain.model.Resource
import com.cataractaction.core.domain.model.SignInStateGoogle
import com.cataractaction.core.domain.usecase.AuthGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoogleViewModel @Inject constructor(private val authGoogleUseCase: AuthGoogleUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow(SignInStateGoogle())
    val state = _state.asStateFlow()

    fun resetState() {
        _state.update { SignInStateGoogle() }
    }

    suspend fun googleWithIntent(intent: Intent) = viewModelScope.launch {
        authGoogleUseCase.googleWithIntent(intent).collect { result ->
            when (result) {
                is Resource.Success ->
                    _state.update {
                        SignInStateGoogle(isSignInSuccessful = true)
                    }

                is Resource.Error ->
                    _state.update {
                        SignInStateGoogle(signInError = result.error)
                    }

                is Resource.Loading ->
                    _state.update {
                        SignInStateGoogle(isLoading = true)
                    }
            }
        }
    }

    suspend fun setupIntentSender(context: Context): IntentSender? =
        authGoogleUseCase.setupIntentSender(context)

    suspend fun signUpWithEmailAndPassword(
        email: String,
        password: String
    ) = viewModelScope.launch {
        authGoogleUseCase.signUpWithEmailAndPassword(email, password).collect { result ->
            when (result) {
                is Resource.Success ->
                    _state.update {
                        SignInStateGoogle(isSignInSuccessful = true)
                    }

                is Resource.Error ->
                    _state.update {
                        SignInStateGoogle(signInError = result.error)
                    }

                is Resource.Loading ->
                    _state.update {
                        SignInStateGoogle(isLoading = true)
                    }
            }
        }
    }

    suspend fun signInWithEmailAndPassword(email: String, password: String) =
        viewModelScope.launch {
            authGoogleUseCase.signInWithEmailAndPassword(email, password).collect { result ->
                when (result) {
                    is Resource.Success ->
                        _state.update {
                            SignInStateGoogle(isSignInSuccessful = true)
                        }

                    is Resource.Error ->
                        _state.update {
                            SignInStateGoogle(signInError = result.error)
                        }

                    is Resource.Loading ->
                        _state.update {
                            SignInStateGoogle(isLoading = true)
                        }
                }
            }
        }
}