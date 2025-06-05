package com.konkuk.codion.ui.onboarding.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.konkuk.codion.ui.navigation.MainTabRoute
import com.konkuk.codion.ui.navigation.Routes
import com.konkuk.codion.ui.onboarding.screen.LoginScreen
import com.konkuk.codion.ui.onboarding.screen.RegisterMainScreen
import com.konkuk.codion.ui.onboarding.screen.RegisterScreen
import com.konkuk.codion.ui.onboarding.screen.RegisterWithEmailScreen
import com.konkuk.codion.ui.onboarding.screen.SplashScreen

fun NavController.navigateToLogin() {
    navigate(Routes.Login)
}

fun NavController.navigateOnboardingToHome() {
    navigate(MainTabRoute.Home) {
        popUpTo(Routes.Splash) {
            inclusive = true
        }
    }
}

fun NavController.navigateToRegisterMain() {
    navigate(Routes.RegisterMain)
}

fun NavController.navigateToRegisterWithEmail() {
    navigate(Routes.RegisterWithEmail)
}

fun NavController.navigateToRegister() {
    navigate(Routes.Register)
}

fun NavGraphBuilder.onboardingNavGraph(
    navigateBack: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateOnboardingToHome: () -> Unit,
    navigateToRegisterMain: () -> Unit,
    navigateToRegisterWithEmail: () -> Unit,
    navigateToRegister: () -> Unit
) {
    composable<Routes.Splash> {
        SplashScreen(
            navigateToLogin = navigateToLogin
        )
    }
    composable<Routes.Login> {
        LoginScreen(
            navigateToHome = navigateOnboardingToHome,
            navigateToRegisterMain = navigateToRegisterMain
        )
    }
    composable<Routes.RegisterMain> {
        RegisterMainScreen(
            onBackClick = navigateBack,
            navigateToRegisterWithEmail = navigateToRegisterWithEmail
        )
    }
    composable<Routes.RegisterWithEmail> {
        RegisterWithEmailScreen(
            onBackClick = navigateBack,
            navigateToRegister = navigateToRegister
        )
    }
    composable<Routes.Register> {
        RegisterScreen(
            onBackClick = navigateBack
        )
    }
}