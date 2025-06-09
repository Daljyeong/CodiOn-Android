package com.konkuk.codion.ui.codiRecord.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.konkuk.codion.ui.codiRecord.screen.CodiRecordScreen
import com.konkuk.codion.ui.codiRecord.screen.RegisterCodiCommentScreen
import com.konkuk.codion.ui.codiRecord.screen.RegisterCodiScreen
import com.konkuk.codion.ui.common.TopAppBarState
import com.konkuk.codion.ui.navigation.MainTabRoute
import com.konkuk.codion.ui.navigation.Routes

fun NavController.navigateToCodiRecord(navOptions: NavOptions) {
    navigate(MainTabRoute.CodiRecord, navOptions)
}

fun NavController.navigateToRegisterCodi() {
    navigate(Routes.RegisterCodi)
}

fun NavController.navigateToRegisterCodiComment() {
    navigate(Routes.RegisterCodiComment)
}

fun NavGraphBuilder.codiRecordNavGraph(
    padding: PaddingValues,
    navigateToCodiRecord: () -> Unit,
    navigateToRegisterCodiComment: () -> Unit,
    navigateToRegisterCodi: () -> Unit,
    setTopAppBar: (TopAppBarState?) -> Unit
) {
    composable<MainTabRoute.CodiRecord> {
        CodiRecordScreen(
            padding = padding,
            onAddClick = navigateToRegisterCodi,
            setTopAppBar = setTopAppBar
        )
    }

    composable<Routes.RegisterCodi> {
        RegisterCodiScreen(
            padding = padding,
            onNextClick = navigateToRegisterCodiComment,
            setTopAppBar = setTopAppBar
        )
    }

    composable<Routes.RegisterCodiComment> {
        RegisterCodiCommentScreen(
            padding = padding,
            onNextClick = navigateToCodiRecord,
            setTopAppBar = setTopAppBar
        )
    }
}