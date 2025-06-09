package com.konkuk.codion.ui.personalColor.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarComponent
import com.konkuk.codion.ui.common.buttons.BigButtonComponent
import com.konkuk.codion.ui.personalColor.component.ColorFeatureComponent
import com.konkuk.codion.ui.personalColor.component.ColorPaletteComponent
import com.konkuk.codion.ui.personalColor.component.ColorResultTagLine
import com.konkuk.codion.ui.personalColor.dummy.ColorDummyData
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray100
import com.konkuk.codion.ui.theme.Gray300
import com.konkuk.codion.ui.theme.Gray700
import com.konkuk.codion.ui.theme.Gray900

@Composable
fun ColorResultScreen(
    personalColorData: ColorDummyData,
    onBackClick: () -> Unit,  // 뒤로 가기
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
        Column(
            modifier = Modifier.padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 메인 이미지
            Image(
                painter = painterResource(personalColorData.mainImageResourceId),
                contentDescription = "퍼스널컬러 분석 메인 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            // 퍼스널컬러 설명
            Column(
                modifier = Modifier
                    .padding(horizontal = 40.dp)
                    .fillMaxWidth()
            ) {
                // 퍼스널컬러
                Text(
                    text = stringResource(personalColorData.personalColorId) + stringResource(R.string.tone),
                    style = CodiOnTypography.pretendard_600_20.copy(
                        lineHeight = 30.sp,
                        lineHeightStyle = LineHeightStyle(
                            alignment = LineHeightStyle.Alignment.Center,
                            trim = LineHeightStyle.Trim.None
                        )
                    ),
                    color = Gray700,
                    modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(20.dp))

                // 퍼스널컬러 특징
                ColorResultTagLine(
                    personalColor = stringResource(personalColorData.personalColorId),
                    tag = stringResource(R.string.feature)
                )
                Spacer(modifier = Modifier.height(4.dp))
                ColorFeatureComponent(ColorDummyData.autumnDummyData)

                Spacer(modifier = Modifier.height(20.dp))

                // 퍼스널컬러 팔레트
                ColorResultTagLine(
                    personalColor = stringResource(personalColorData.personalColorId),
                    tag = stringResource(R.string.palette)
                )
                Spacer(modifier = Modifier.height(4.dp))
                ColorPaletteComponent(
                    personalColorData = personalColorData
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // buttons
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
            ) {
                // 다시 분석하기 버튼
                BigButtonComponent(
                    containerColor = Gray300,
                    contentColor = Gray700,
                    text = stringResource(R.string.reanalyze)
                )
                Spacer(modifier = Modifier.height(12.dp))
                // 완료 버튼
                BigButtonComponent(
                    containerColor = Gray900,
                    contentColor = Gray100,
                    text = stringResource(R.string.code_btn_done)
                )
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Preview
@Composable
fun ColorResultScreenPreview() {
    ColorResultScreen(
        personalColorData = ColorDummyData.autumnDummyData,
        onBackClick = {}
    )
}
