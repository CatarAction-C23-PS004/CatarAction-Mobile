package com.cataractaction.core.di

import android.content.Context
import com.cataractaction.core.data.AuthGoogleRepository
import com.cataractaction.core.domain.repository.IAuthGoogleRepository
import com.cataractaction.core.domain.usecase.AuthGoogleInteractor
import com.cataractaction.core.domain.usecase.AuthGoogleUseCase
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideSignInClient(@ApplicationContext context: Context): SignInClient =
        Identity.getSignInClient(context)

    @Provides
    @Singleton
    fun provideAuthGoogleRepository(@ApplicationContext context: Context): IAuthGoogleRepository =
        AuthGoogleRepository(provideSignInClient(context))

    @Provides
    @Singleton
    fun provideAuthGoogleUseCase(@ApplicationContext context: Context): AuthGoogleUseCase =
        AuthGoogleInteractor(provideAuthGoogleRepository(context))

}