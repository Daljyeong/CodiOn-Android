package com.konkuk.codion.ui.personalColor.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.codion.ui.navigation.Routes
import com.konkuk.codion.ui.personalColor.screen.ColorGuideScreen

fun NavController.navigateToColorGuide() {
    navigate(Routes.ColorGuide)
}

fun NavGraphBuilder.personalColorNavGraph(
    navigateBack: () -> Unit
) {
    composable<Routes.ColorGuide> {
        ColorGuideScreen(
            onBackClick = navigateBack
        )
    }
}