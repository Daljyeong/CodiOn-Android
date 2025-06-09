package com.konkuk.codion.ui.personalColor.camera

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CameraModule {
    @Binds
    abstract fun bindCameraCapture(
        cameraCaptureImpl: CameraCaptureImpl
    ): CameraCapture
}