package com.konkuk.codion.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Routes {
    // onboarding
    @Serializable
    data object Splash: Routes
    @Serializable
    data object Login : Routes
    @Serializable
    data object RegisterMain : Routes
    @Serializable
    data object RegisterWithEmail : Routes
    @Serializable
    data object Register : Routes

    // personalColor
    @Serializable
    data object ColorGuide : Routes
    @Serializable
    data object ColorCamera : Routes
    @Serializable
    data object ColorResult : Routes

    // Home
    @Serializable
    data object Home : Routes

    // codiRecord
    @Serializable
    data object CodiRecord : Routes
    @Serializable
    data object RegisterCodi : Routes
    @Serializable
    data object RegisterCodiComment : Routes

    // myCloset
    @Serializable
    data object MyCloset : Routes
    @Serializable
    data object AddMyClothesDetail : Routes
    @Serializable
    data object AddMyClothesPicture : Routes
}