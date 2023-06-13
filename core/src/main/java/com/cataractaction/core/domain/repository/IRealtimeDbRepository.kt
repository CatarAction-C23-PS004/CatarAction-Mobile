package com.cataractaction.core.domain.repository

import android.net.Uri
import com.cataractaction.core.data.Resource
import com.cataractaction.core.domain.model.RealtimeDBArticle
import com.cataractaction.core.domain.model.RealtimeDBHistory
import com.cataractaction.core.domain.model.RealtimeDBUser
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IRealtimeDbRepository {

    suspend fun getUsers(): Flow<Resource<List<RealtimeDBUser?>>>

    fun createUser(realtimeDB: RealtimeDBUser): Flow<Resource<String>>

    suspend fun getArticles(): Flow<Resource<List<RealtimeDBArticle?>>>

    fun createHistory(imageFile: File, realtimeDB: RealtimeDBHistory): Flow<Resource<String>>

    suspend fun getHistory(): Flow<Resource<List<RealtimeDBHistory?>>>
}