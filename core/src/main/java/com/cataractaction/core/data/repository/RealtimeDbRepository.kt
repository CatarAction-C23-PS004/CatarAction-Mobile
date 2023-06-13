package com.cataractaction.core.data.repository

import android.net.Uri
import com.cataractaction.core.data.Resource
import com.cataractaction.core.domain.model.RealtimeDBArticle
import com.cataractaction.core.domain.model.RealtimeDBHistory
import com.cataractaction.core.domain.model.RealtimeDBUser
import com.cataractaction.core.domain.repository.IRealtimeDbRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.io.File
import javax.inject.Inject

class RealtimeDbRepository @Inject constructor(
    private val userReference: DatabaseReference,
    private val articleReference: DatabaseReference,
    private val historyReference: DatabaseReference
) :
    IRealtimeDbRepository {

    override suspend fun getUsers(): Flow<Resource<List<RealtimeDBUser?>>> = callbackFlow {
        trySend(Resource.Loading)
        userReference.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val users = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<RealtimeDBUser>()
                }
                trySend(Resource.Success(users))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
            }
        }
        userReference.addValueEventListener(event)
        awaitClose { close() }
    }


    override fun createUser(realtimeDB: RealtimeDBUser): Flow<Resource<String>> = callbackFlow {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val user = RealtimeDBUser(id = userId, name = realtimeDB.name)
        userReference.child(userId!!).setValue(user)
            .addOnSuccessListener { trySend(Resource.Success("User Added")) }
            .addOnFailureListener { trySend(Resource.Error(it.message.toString())) }
        awaitClose { close() }
    }

    override suspend fun getArticles(): Flow<Resource<List<RealtimeDBArticle?>>> = callbackFlow {
        trySend(Resource.Loading)
        articleReference.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val articles = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<RealtimeDBArticle>()
                }
                trySend(Resource.Success(articles))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
            }
        }
        articleReference.addValueEventListener(event)
        awaitClose { close() }
    }


    override fun createHistory(
        imageFile: File,
        realtimeDB: RealtimeDBHistory
    ): Flow<Resource<String>> =
        callbackFlow {
            trySend(Resource.Loading)

            val userId = FirebaseAuth.getInstance().currentUser
            val storage = FirebaseStorage.getInstance().reference
            val imageRef = storage.child("images/${imageFile.name}")
            val uploadTask = imageRef.putFile(Uri.fromFile(imageFile))
            val data = RealtimeDBHistory(
                id = userId?.uid,
                result = realtimeDB.result,
                date = realtimeDB.date
            )
            uploadTask.addOnSuccessListener { taskSnapshot ->
                taskSnapshot.storage.downloadUrl.addOnSuccessListener { uri ->
                    historyReference.child(historyReference.push().key!!).setValue(
                        data.copy(image = uri.toString())
                    )
                }
                trySend(Resource.Success("History Added"))
            }.addOnFailureListener {
                trySend(Resource.Error(it.message.toString()))
            }
            awaitClose { close() }
        }

    override suspend fun getHistory(): Flow<Resource<List<RealtimeDBHistory?>>> = callbackFlow {
        trySend(Resource.Loading)
        historyReference.keepSynced(true)
        val event = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val history = snapshot.children.map { dataSnapshot ->
                    dataSnapshot.getValue<RealtimeDBHistory>()
                }.filter { it?.id == FirebaseAuth.getInstance().currentUser?.uid }

                trySend(Resource.Success(history))
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(Resource.Error(error.message))
            }
        }
        historyReference.addValueEventListener(event)
        awaitClose { close() }
    }
}