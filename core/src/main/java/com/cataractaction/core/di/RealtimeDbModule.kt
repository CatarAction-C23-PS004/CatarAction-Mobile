package com.cataractaction.core.di

import com.cataractaction.core.data.repository.RealtimeDbRepository
import com.cataractaction.core.domain.repository.IRealtimeDbRepository
import com.cataractaction.core.domain.usecase.RealtimeDbInteractor
import com.cataractaction.core.domain.usecase.RealtimeDbUseCase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealtimeDbModule {
    @Singleton
    @Provides
    fun provideRealtimeDbUserReference() = Firebase.database.getReference("user")

    @Singleton
    @Provides
    fun provideRealtimeDbArticleReference() = Firebase.database.getReference("article")


    @Singleton
    @Provides
    fun provideHistoryDbArticleReference() = Firebase.database.getReference("history")

    @Provides
    @Singleton
    fun provideRealtimeDbRepository(): IRealtimeDbRepository =
        RealtimeDbRepository(
            userReference = provideRealtimeDbUserReference(),
            articleReference = provideRealtimeDbArticleReference(),
            historyReference = provideHistoryDbArticleReference()
        )

    @Provides
    @Singleton
    fun provideRealtimeDbUseCase(): RealtimeDbUseCase =
        RealtimeDbInteractor(provideRealtimeDbRepository())
}