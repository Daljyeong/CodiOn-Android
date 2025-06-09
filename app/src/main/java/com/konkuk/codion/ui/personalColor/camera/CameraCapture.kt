package com.konkuk.codion.ui.personalColor.camera

import android.net.Uri
import androidx.camera.view.PreviewView
import androidx.lifecycle.LifecycleOwner

interface CameraCapture {
    fun initialize(lifecycleOwner: LifecycleOwner)
    fun getPreviewView(): PreviewView
    fun takePicture(onImageCaptured: (Uri) -> Unit)
    fun unBindCamera()
}