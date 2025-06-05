package com.konkuk.codion.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.konkuk.codion.ui.codiRecord.navigation.navigateToCodiRecord
import com.konkuk.codion.ui.home.navigation.navigateToHome
import com.konkuk.codion.ui.myCloset.navigation.navigateToAddMyClothesPicture
import com.konkuk.codion.ui.myCloset.navigation.navigateToMyCloset
import com.konkuk.codion.ui.mypage.navigation.navigateToMypage
import com.konkuk.codion.ui.onboarding.navigation.navigateOnboardingToHome
import com.konkuk.codion.ui.onboarding.navigation.navigateToLogin
import com.konkuk.codion.ui.onboarding.navigation.navigateToRegisterMain

class MainNavController(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination = Routes.Splash

    val currentTab: MainTab?
        @Composable get() = MainTab.entries.find { tab ->
            currentDestination?.route == tab.route::class.qualifiedName
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTab.HOME.route) {
                inclusive = false
            }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navController.navigateToHome(navOptions)
            MainTab.CODI_RECORD -> navController.navigateToCodiRecord(navOptions)
            MainTab.MY_CLOSET -> navController.navigateToMyCloset(navOptions)
            MainTab.MY_PAGE -> navController.navigateToMypage(navOptions)
        }
    }

    // Back Pressed
    fun navigateUp() {
        navController.navigateUp()
    }

    // Onboarding
    fun navigateToLogin() {
        navController.navigateToLogin()
    }

    fun navigateOnboardingToHome() {
        navController.navigateOnboardingToHome()
    }

    fun navigateToRegisterMain() {
        navController.navigateToRegisterMain()
    }

    fun navigateToAddMyClothesPicture() {
        navController.navigateToAddMyClothesPicture()
    }

    @Composable
    fun shouldShowBottomBar(): Boolean = MainTab.contains {
        currentDestination?.route?.startsWith(it::class.qualifiedName!!) == true
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavController = remember(navController) {
    MainNavController(navController)
}