package com.cataractaction.ui.screen.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.cataractaction.core.data.Resource
import com.cataractaction.core.domain.model.RealtimeDBArticleState
import com.cataractaction.core.domain.model.RealtimeDBHistory
import com.cataractaction.core.domain.model.RealtimeDBHistoryState
import com.cataractaction.core.domain.model.RealtimeDBUser
import com.cataractaction.core.domain.model.RealtimeDBUserState
import com.cataractaction.core.domain.usecase.RealtimeDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class RealtimeDbViewModel @Inject constructor(
    private val realtimeDbUseCase: RealtimeDbUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow(RealtimeDBUserState())
    val state = _state.asStateFlow()

    private val _stateArticle = MutableStateFlow(RealtimeDBArticleState())
    val stateArticle = _stateArticle.asStateFlow()

    private val _stateHistory = MutableStateFlow(RealtimeDBHistoryState())
    val stateHistory = _stateHistory.asStateFlow()

    init {
        getUsers()
        getArticles()
        getHistory()
    }

    private fun getUsers() = viewModelScope.launch {
        realtimeDbUseCase.getUsers().collect { result ->
            when (result) {
                is Resource.Success ->
                    _state.update {
                        RealtimeDBUserState(data = result.data)
                    }

                is Resource.Error ->
                    _state.update {
                        RealtimeDBUserState(errorMsg = result.error)
                    }

                is Resource.Loading ->
                    _state.update {
                        RealtimeDBUserState(isLoading = true)
                    }
            }
        }
    }

    fun createUser(user: RealtimeDBUser, context: Context) = viewModelScope.launch {
        realtimeDbUseCase.createUser(user).collect { result ->
            when (result) {
                is Resource.Success -> {
                    Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                }

                is Resource.Error -> {
                    Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                }

                is Resource.Loading -> {
                }
            }
        }
    }

    private fun getArticles() = viewModelScope.launch {
        realtimeDbUseCase.getArticles().collect { result ->
            when (result) {
                is Resource.Success ->
                    _stateArticle.update {
                        RealtimeDBArticleState(data = result.data)
                    }

                is Resource.Error ->
                    _stateArticle.update {
                        RealtimeDBArticleState(errorMsg = result.error)
                    }

                is Resource.Loading ->
                    _stateArticle.update {
                        RealtimeDBArticleState(isLoading = true)
                    }
            }
        }
    }


    fun createHistory(
        onClick: () -> Unit,
        imageFile: File,
        user: RealtimeDBHistory,
        context: Context
    ) =
        viewModelScope.launch {
            realtimeDbUseCase.createHistory(imageFile, user).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        Toast.makeText(context, result.data, Toast.LENGTH_SHORT).show()
                        _stateHistory.update {
                            RealtimeDBHistoryState(isLoading = false)
                        }
                       onClick()
                    }

                    is Resource.Error -> {
                        Toast.makeText(context, result.error, Toast.LENGTH_SHORT).show()
                    }

                    is Resource.Loading -> {
                        _stateHistory.update {
                            RealtimeDBHistoryState(isLoading = true)
                        }
                    }
                }
            }
        }

    private fun getHistory() = viewModelScope.launch {
        realtimeDbUseCase.getHistory().collect { result ->
            when (result) {
                is Resource.Success ->
                    _stateHistory.update {
                        RealtimeDBHistoryState(data = result.data)
                    }

                is Resource.Error ->
                    _stateHistory.update {
                        RealtimeDBHistoryState(errorMsg = result.error)
                    }

                is Resource.Loading ->
                    _stateHistory.update {
                        RealtimeDBHistoryState(isLoading = true)
                    }
            }
        }
    }

}