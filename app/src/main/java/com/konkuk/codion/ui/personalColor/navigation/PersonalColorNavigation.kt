package com.konkuk.codion.ui.personalColor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.codion.ui.navigation.Routes
import com.konkuk.codion.ui.personalColor.dummy.ColorDummyData
import com.konkuk.codion.ui.personalColor.screen.ColorCameraScreen
import com.konkuk.codion.ui.personalColor.screen.ColorGuideScreen
import com.konkuk.codion.ui.personalColor.screen.ColorResultScreen

fun NavController.navigateToColorGuide() {
    navigate(Routes.ColorGuide)
}

fun NavController.navigateToColorCamera() {
    navigate(Routes.ColorCamera)
}

fun NavController.navigateToColorResult() {
    navigate(Routes.ColorResult)
}

fun NavGraphBuilder.personalColorNavGraph(
    navigateBack: () -> Unit,
    navigateToColorCamera: () -> Unit,
    navigateToColorResult: () -> Unit
) {
    composable<Routes.ColorGuide> {
        ColorGuideScreen(
            onBackClick = navigateBack,
            navigateToColorCamera = navigateToColorCamera
        )
    }
    composable<Routes.ColorCamera> {
        ColorCameraScreen(
            onBackClick = navigateBack,
            navigateToColorResult = navigateToColorResult
        )
    }
    composable<Routes.ColorResult> {
        ColorResultScreen(
            personalColorData = ColorDummyData.autumnDummyData,
            onBackClick = navigateBack
        )
    }
}