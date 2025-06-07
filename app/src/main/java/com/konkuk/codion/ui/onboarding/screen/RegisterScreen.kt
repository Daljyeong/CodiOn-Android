package com.konkuk.codion.ui.onboarding.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarComponent
import com.konkuk.codion.ui.common.buttons.BigButtonComponent
import com.konkuk.codion.ui.common.buttons.BigButtonWithIconComponent
import com.konkuk.codion.ui.common.inputFields.InputFieldComponent
import com.konkuk.codion.ui.onboarding.component.TermsBoxComponent
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray100
import com.konkuk.codion.ui.theme.Gray700
import com.konkuk.codion.ui.theme.Gray900

@Composable
fun RegisterScreen(
    onBackClick: () -> Unit,  // 뒤로 가기
    navigateToColorGuide: () -> Unit
) {
    var nickname by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = stringResource(R.string.register),
                leftIcon = painterResource(R.drawable.ic_back),
                onLeftClicked = { onBackClick() },
                rightIcon = null,
                onRightClicked = null
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            // 약관 동의
            Text(
                text = stringResource(R.string.terms) + " " + stringResource(R.string.asterisk),
                style = CodiOnTypography.pretendard_600_14,
                color = Gray700
            )
            Spacer(modifier = Modifier.height(4.dp))

            TermsBoxComponent()

            Spacer(modifier = Modifier.height(20.dp))

            // 별명
            InputFieldComponent(
                label = stringResource(R.string.nickname),
                isRequired = true,
                placeholder = stringResource(R.string.nickname_ph),
                inputText = nickname,
                onTextChange = { nickname = it }
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 퍼스널컬러
            Text(
                text = stringResource(R.string.personal_color) + " " + stringResource(R.string.asterisk),
                style = CodiOnTypography.pretendard_600_14,
                color = Gray700
            )
            Spacer(modifier = Modifier.height(4.dp))
            BigButtonWithIconComponent(
                text = stringResource(id = R.string.enter_yourself),
                icon = painterResource(id = R.drawable.ic_write)
            )
            Spacer(modifier = Modifier.height(4.dp))
            BigButtonWithIconComponent(
                text = stringResource(id = R.string.camera_personal_color),
                icon = painterResource(id = R.drawable.ic_camera),
                onClick = { navigateToColorGuide() }
            )

            Spacer(modifier = Modifier.weight(1f))

            // 회원가입 완료 버튼
            BigButtonComponent(
                containerColor = Gray900,
                contentColor = Gray100,
                text = stringResource(R.string.register_done)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onBackClick = {},
        navigateToColorGuide = {}
    )
}