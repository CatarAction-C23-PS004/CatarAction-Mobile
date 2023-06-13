package com.cataractaction.core.domain.usecase

import com.cataractaction.core.data.source.local.preferences.ProfilePreferences
import kotlinx.coroutines.flow.Flow

class ProfilePreferencesInteractor(private val profilePreferences: ProfilePreferences) :
    ProfilePreferencesUseCase {

    override suspend fun saveAuthProfile(name: String) = profilePreferences.saveAuthProfile(name)

    override fun getAuthName(): Flow<String?> = profilePreferences.getAuthName()
}