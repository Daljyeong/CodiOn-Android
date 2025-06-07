package com.konkuk.codion.ui.personalColor.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarComponent
import com.konkuk.codion.ui.common.buttons.BigButtonComponent
import com.konkuk.codion.ui.common.modifier.ignoreTopPadding
import com.konkuk.codion.ui.personalColor.component.ColorGuidelineBox
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray100
import com.konkuk.codion.ui.theme.Gray700
import com.konkuk.codion.ui.theme.Gray900

@Composable
fun ColorGuideScreen(
    onBackClick: () -> Unit  // 뒤로 가기
) {
    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = stringResource(R.string.camera_personal_color),
                leftIcon = painterResource(R.drawable.ic_back),
                onLeftClicked = { onBackClick() },
                rightIcon = null,
                onRightClicked = null
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .ignoreTopPadding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = stringResource(R.string.start_personal_color),
                    style = CodiOnTypography.pretendard_600_20.copy(
                        lineHeight = 30.sp,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    ),
                    color = Gray700,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                ColorGuidelineBox()

                Spacer(modifier = Modifier.weight(1f))

                BigButtonComponent(
                    containerColor = Gray900,
                    contentColor = Gray100,
                    text = stringResource(R.string.start_analyze)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview
@Composable
fun ColorGuideScreenPreview() {
    ColorGuideScreen(
        onBackClick = {}
    )
}