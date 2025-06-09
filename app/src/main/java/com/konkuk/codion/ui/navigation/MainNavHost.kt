package com.konkuk.codion.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.konkuk.codion.ui.codiRecord.navigation.codiRecordNavGraph
import com.konkuk.codion.ui.common.TopAppBarState
import com.konkuk.codion.ui.home.navigation.homeNavGraph
import com.konkuk.codion.ui.myCloset.navigation.myClosetNavGraph
import com.konkuk.codion.ui.mypage.navigation.mypageNavGraph
import com.konkuk.codion.ui.onboarding.navigation.onboardingNavGraph
import com.konkuk.codion.ui.personalColor.navigation.personalColorNavGraph
import com.konkuk.codion.ui.theme.Gray100

@Composable
fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavController,
    padding: PaddingValues,
    setTopAppBar: (TopAppBarState?) -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Gray100)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            onboardingNavGraph(
                navigateBack = navigator::navigateUp,
                navigateToLogin = navigator::navigateToLogin,
                navigateOnboardingToHome = navigator::navigateOnboardingToHome,
                navigateToRegisterMain = navigator::navigateToRegisterMain,
                navigateToRegisterWithEmail = navigator::navigateToRegisterWithEmail,
                navigateToRegister = navigator::navigateToRegister,
                navigateToColorGuide = navigator::navigateToColorGuide
            )

            personalColorNavGraph(
                navigateBack = navigator::navigateUp,
                navigateToColorCamera = navigator::navigateToColorCamera,
                navigateToColorResult = navigator::navigateToColorResult
            )

            homeNavGraph(
                padding = padding,
                setTopAppBar = setTopAppBar
            )

            codiRecordNavGraph(
                padding = padding,
                navigateToCodiRecord = navigator::navigateBackToCodiRecordTab,
                navigateToRegisterCodiComment = navigator::navigateToRegisterCodiComment,
                navigateToRegisterCodi = navigator::navigateToRegisterCodi,
                setTopAppBar = setTopAppBar
            )

            myClosetNavGraph(
                padding = padding,
                navigateBack = navigator::navigateUp,
                navigateToAddMyClothesPicture = navigator::navigateToAddMyClothesPicture,
                setTopAppBar = setTopAppBar
            )

            mypageNavGraph(
                padding = padding,
                setTopAppBar = setTopAppBar
            )
        }
    }
}