package com.cataractaction.core.domain.model

data class RealtimeDBUserState(
    val data: List<RealtimeDBUser?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)

data class RealtimeDBArticleState(
    val data: List<RealtimeDBArticle?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)

data class RealtimeDBHistoryState(
    val data: List<RealtimeDBHistory?>? = null,
    val isLoading: Boolean = false,
    val errorMsg: String? = null
)
