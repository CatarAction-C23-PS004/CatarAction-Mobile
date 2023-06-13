package com.cataractaction.core.domain.usecase

import com.cataractaction.core.data.Resource
import com.cataractaction.core.domain.model.RealtimeDBArticle
import com.cataractaction.core.domain.model.RealtimeDBHistory
import com.cataractaction.core.domain.model.RealtimeDBUser
import com.cataractaction.core.domain.repository.IRealtimeDbRepository
import kotlinx.coroutines.flow.Flow
import java.io.File

class RealtimeDbInteractor(private val realtimeDbRepository: IRealtimeDbRepository) :
    RealtimeDbUseCase {

    override suspend fun getUsers(): Flow<Resource<List<RealtimeDBUser?>>> =
        realtimeDbRepository.getUsers()

    override fun createUser(realtimeDB: RealtimeDBUser): Flow<Resource<String>> =
        realtimeDbRepository.createUser(realtimeDB)

    override suspend fun getArticles(): Flow<Resource<List<RealtimeDBArticle?>>> =
        realtimeDbRepository.getArticles()

    override fun createHistory(
        imageFile: File,
        realtimeDB: RealtimeDBHistory
    ): Flow<Resource<String>> =
        realtimeDbRepository.createHistory(imageFile, realtimeDB)

    override suspend fun getHistory(): Flow<Resource<List<RealtimeDBHistory?>>> =
        realtimeDbRepository.getHistory()
}