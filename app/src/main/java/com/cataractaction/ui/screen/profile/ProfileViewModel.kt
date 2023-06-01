package com.cataractaction.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.cataractaction.core.data.AuthGoogleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (private val authGoogleRepository: AuthGoogleRepository) : ViewModel() {

    suspend fun signOut() = authGoogleRepository.signOut()

    fun getSignedInUser() = authGoogleRepository.getSignedInUser()
}