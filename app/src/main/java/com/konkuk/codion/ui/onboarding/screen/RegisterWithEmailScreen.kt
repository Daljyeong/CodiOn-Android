package com.konkuk.codion.ui.onboarding.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarComponent
import com.konkuk.codion.ui.common.buttons.BigButtonComponent
import com.konkuk.codion.ui.common.inputFields.InputFieldComponent
import com.konkuk.codion.ui.common.inputFields.InputFieldWithBtnComponent
import com.konkuk.codion.ui.theme.Gray100
import com.konkuk.codion.ui.theme.Gray900

@Composable
fun RegisterWithEmailScreen(
    onBackClick: () -> Unit  // 뒤로 가기
) {
    var email by remember { mutableStateOf("") }
    var isTimerVisible by remember { mutableStateOf(false) }
    var emailCode by remember { mutableStateOf<Int?>(null) }
    var emailCodeToString by remember { mutableStateOf("") }
    var pwd by remember { mutableStateOf("") }
    var isPwdVisible by remember { mutableStateOf(false) }
    var pwdCheck by remember { mutableStateOf("") }
    var isPwdCheckVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBarComponent(
                title = stringResource(R.string.register_email),
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
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            InputFieldWithBtnComponent(
                label = stringResource(R.string.email),
                placeholder = stringResource(R.string.email_ph),
                inputText = email,
                onTextChange = { email = it },
                btnText = stringResource(R.string.code_btn_send),
                onBtnClick = { isTimerVisible = true }
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputFieldWithBtnComponent(
                label = stringResource(R.string.code),
                placeholder = stringResource(R.string.code_ph),
                inputText = emailCodeToString,
                onTextChange = {
                    emailCodeToString = it
                    emailCode = it.toIntOrNull() ?: 0
                },
                btnText = stringResource(R.string.code_btn_done),
                showTimer = isTimerVisible,
                timerSeconds = 180
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputFieldComponent(
                label = stringResource(R.string.pwd),
                isRequired = true,
                placeholder = stringResource(R.string.pwd_ph_hint),
                inputText = pwd,
                onTextChange = { pwd = it },
                isPwdField = true,
                isPwdVisible = isPwdVisible,
                onTogglePwdVisibility = { isPwdVisible = !isPwdVisible }
            )
            Spacer(modifier = Modifier.height(20.dp))
            InputFieldComponent(
                label = stringResource(R.string.pwd_ph_check),
                isRequired = true,
                placeholder = stringResource(R.string.pwd_ph_hint),
                inputText = pwdCheck,
                onTextChange = { pwdCheck = it },
                isPwdField = true,
                isPwdVisible = isPwdCheckVisible,
                onTogglePwdVisibility = { isPwdCheckVisible = !isPwdCheckVisible }
            )
            Spacer(modifier = Modifier.weight(1f))
            BigButtonComponent(
                containerColor = Gray900,
                contentColor = Gray100,
                text = stringResource(R.string.next)
            )
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}

@Preview
@Composable
fun RegisterWithEmailScreenPreview() {
    RegisterWithEmailScreen(
        onBackClick = {}
    )
}