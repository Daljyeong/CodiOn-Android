package com.konkuk.codion.ui.personalColor.screen

import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarComponent
import com.konkuk.codion.ui.common.buttons.SmallButtonComponent
import com.konkuk.codion.ui.personalColor.viewmodel.CameraViewModel
import com.konkuk.codion.ui.theme.Gray300
import com.konkuk.codion.ui.theme.Gray700

@Composable
fun ColorCameraScreen(
    onBackClick: () -> Unit,  // 뒤로 가기
    navigateToColorResult: () -> Unit,
    cameraViewModel: CameraViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val cameraX by cameraViewModel.cameraX.observeAsState()
    val previewView by cameraViewModel.preview.observeAsState()

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // 권한 허용 -> 카메라 초기화하기
                cameraViewModel.initialize(lifecycleOwner)
            } else {
                // TODO: 권한 거부되었을 때 처리하기
            }
        }
    )

    LaunchedEffect(Unit) {
        cameraViewModel.closeCameraView()
        permissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

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
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            // 카메라 프리뷰
            previewView?.let { preview ->
                AndroidView(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxSize(),
                    factory = { preview }
                )
            }

            // 촬영하기 버튼
            Column {
                Spacer(modifier = Modifier.weight(1f))

                SmallButtonComponent(
                    modifier = Modifier.width(200.dp),
                    containerColor = Gray300,
                    contentColor = Gray700,
                    text = stringResource(R.string.take_photo),
                    onClick = { navigateToColorResult() }
                )

                Spacer(modifier = Modifier.height(54.dp))
            }
        }
    }
}

@Preview
@Composable
fun ColorCameraScreenPreview() {
    ColorCameraScreen(
        onBackClick = {},
        navigateToColorResult = {}
    )
}