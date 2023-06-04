package com.cataractaction.ui.screen.camera

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class CameraState(
    val isCameraGranted: Boolean = false,
    val isCameraDenied: Boolean = false,
)

@HiltViewModel
class CameraViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CameraState())
    val state = _state.asStateFlow()

    fun resetState() {
        _state.update { CameraState() }
    }
}