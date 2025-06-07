package com.konkuk.codion.ui.personalColor.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarComponent
import com.konkuk.codion.ui.common.buttons.SmallButtonComponent
import com.konkuk.codion.ui.personalColor.component.CustomProgressBar
import com.konkuk.codion.ui.theme.Gray100
import com.konkuk.codion.ui.theme.Gray500
import com.konkuk.codion.ui.theme.Gray700

@Composable
fun ColorCameraScreen(
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
        // TODO: 카메라 화면 가져오기 (서버 측에서 어떻게 제공할지 몰라 우선 영역만 잡아둠)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Gray700),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                SmallButtonComponent(
                    modifier = Modifier.width(200.dp),
                    containerColor = Gray500,
                    contentColor = Gray100,
                    text = stringResource(R.string.analyzing)
                )

                Spacer(modifier = Modifier.height(14.dp))

                CustomProgressBar(progress = 0.6f)

                Spacer(modifier = Modifier.height(54.dp))
            }
        }
    }
}

@Preview
@Composable
fun ColorCameraScreenPreview() {
    ColorCameraScreen(
        onBackClick = {}
    )
}