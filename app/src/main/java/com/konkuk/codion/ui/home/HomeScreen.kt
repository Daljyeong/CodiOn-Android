package com.konkuk.codion.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.TopAppBarState
import com.konkuk.codion.ui.home.component.ClothesCardList
import com.konkuk.codion.ui.home.component.WeatherInformation
import com.konkuk.codion.ui.myCloset.viewmodel.MyClosetViewModel
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray300
import com.konkuk.codion.ui.theme.Gray700

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    padding: PaddingValues,
    setTopAppBar: (TopAppBarState?) -> Unit,
    viewModel: MyClosetViewModel = hiltViewModel()
) {
    val scrollState = rememberScrollState()

    val myCloset by viewModel.closet.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        setTopAppBar(
            TopAppBarState(
                titleId = R.string.home
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.home_nickname, "홍길동"),
                style = CodiOnTypography.pretendard_400_16,
                color = Gray700,
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_map_pin),
                contentDescription = null,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(24.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // TODO: 지역 바텀시트 만들기
                Text(
                    text = "서초구",
                    style = CodiOnTypography.pretendard_700_16,
                    color = Gray700,
                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                )
            }

            Text(
                text = stringResource(R.string.sensory_temp_is),
                style = CodiOnTypography.pretendard_400_16,
                color = Gray700,
            )

            Text(
                text = stringResource(R.string.temperature_unit, 28),
                style = CodiOnTypography.pretendard_700_16,
                color = Gray700,
                modifier = Modifier.padding(start = 4.dp)
            )

            Text(
                text = stringResource(R.string.end_word),
                style = CodiOnTypography.pretendard_400_16,
                color = Gray700,
            )
        }

        WeatherInformation(Modifier.padding(top = 8.dp, start = 20.dp, end = 20.dp), 25 , 3, 0)

        Spacer(modifier = Modifier.height(24.dp))
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Gray300,
        )
        Spacer(modifier = Modifier.height(24.dp))

        ClothesCardList(
            title = stringResource(R.string.today_clothes),
            clothesDataList = myCloset.drop(4).take(8) // index 4~11
        )

        Spacer(modifier = Modifier.height(32.dp))

        ClothesCardList(
            title = stringResource(R.string.unusual_clothes),
            clothesDataList = myCloset.drop(12).take(8) // index 12~19
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        padding = PaddingValues(0.dp),
        setTopAppBar = {}
    )
}