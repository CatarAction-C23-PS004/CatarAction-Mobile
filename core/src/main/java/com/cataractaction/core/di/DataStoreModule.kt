package com.cataractaction.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.cataractaction.core.data.source.local.preferences.ProfilePreferences
import com.cataractaction.core.domain.usecase.ProfilePreferencesInteractor
import com.cataractaction.core.domain.usecase.ProfilePreferencesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "cataractaction")

@Module
@InstallIn(SingletonComponent::class)
class DataStoreModule {

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    @Provides
    @Singleton
    fun provideProfilePreferences(dataStore: DataStore<Preferences>): ProfilePreferences =
        ProfilePreferences(dataStore)

    @Provides
    @Singleton
    fun provideProfilePreferencesUseCase(@ApplicationContext context: Context): ProfilePreferencesUseCase =
        ProfilePreferencesInteractor(provideProfilePreferences(provideDataStore(context)))
}