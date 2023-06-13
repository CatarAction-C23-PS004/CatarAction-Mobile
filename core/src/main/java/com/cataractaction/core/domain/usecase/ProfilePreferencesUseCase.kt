package com.cataractaction.core.domain.usecase

import kotlinx.coroutines.flow.Flow

interface ProfilePreferencesUseCase {

    suspend fun saveAuthProfile (name: String)

    fun getAuthName(): Flow<String?>

}