package com.cataractaction.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.cataractaction.core.data.AuthGoogleRepository
import com.cataractaction.core.domain.usecase.AuthGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (private val authGoogleUseCase: AuthGoogleUseCase) : ViewModel() {

    suspend fun signOut() = authGoogleUseCase.signOut()

    fun getSignedInUser() = authGoogleUseCase.getSignedInUser()
}