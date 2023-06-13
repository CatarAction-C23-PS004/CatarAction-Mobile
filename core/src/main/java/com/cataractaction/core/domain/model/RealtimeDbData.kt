package com.cataractaction.core.domain.model

import android.net.Uri
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class RealtimeDBUser(
    val id: String? = null,
    val name: String? = null,
)

@IgnoreExtraProperties
data class RealtimeDBArticle(
    val id: String? = null,
    val title: String? = null,
    val category: String? = null,
    val description: String? = null,
    val image: String? = null
)

@IgnoreExtraProperties
data class RealtimeDBHistory(
    val id: String? = null,
    val result: String? = null,
    val date: String? = null,
    val image: String? = null
)

