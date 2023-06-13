package com.cataractaction.ui.screen.profile

import androidx.lifecycle.ViewModel
import com.cataractaction.core.domain.usecase.AuthGoogleUseCase
import com.cataractaction.core.domain.usecase.ProfilePreferencesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor (private val authGoogleUseCase: AuthGoogleUseCase, private val profilePreferencesUseCase: ProfilePreferencesUseCase) : ViewModel() {

    suspend fun signOut() = authGoogleUseCase.signOut()

    fun getSignedInUser() = authGoogleUseCase.getSignedInUser()

    suspend fun saveAuthProfile(name: String) = profilePreferencesUseCase.saveAuthProfile(name)

    fun getAuthName () = profilePreferencesUseCase.getAuthName()

}