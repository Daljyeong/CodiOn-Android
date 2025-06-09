package com.konkuk.codion.ui.personalColor.viewmodel

import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.konkuk.codion.ui.personalColor.camera.CameraCapture
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val cameraCapture: CameraCapture
) : ViewModel() {
    private val _cameraX = MutableLiveData<CameraCapture?>(null)
    val cameraX: LiveData<CameraCapture?> = _cameraX

    private val _preview = MutableLiveData<PreviewView?>(null)
    val preview: LiveData<PreviewView?> = _preview

    private val _showCameraView = MutableLiveData<Boolean>(false)
    val showCameraView: LiveData<Boolean> = _showCameraView

    init {
        _cameraX.value = cameraCapture
    }

    fun initialize(lifecycleOwner: LifecycleOwner) {
        cameraX.value?.let { capture ->
            capture.initialize(lifecycleOwner)
            _preview.value = capture.getPreviewView()
        }
    }

    fun takePicture() {
        cameraX.value?.takePicture { uri ->
        }
    }

    fun showCameraView() {
        _showCameraView.value = true
    }

    fun closeCameraView() {
        cameraX.value?.unBindCamera()
        _showCameraView.value = false
        _preview.value = null
    }
}