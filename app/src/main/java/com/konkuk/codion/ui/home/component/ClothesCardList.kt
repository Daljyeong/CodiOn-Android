package com.konkuk.codion.ui.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.konkuk.codion.R
import com.konkuk.codion.data.dto.response.ClosetResponse
import com.konkuk.codion.ui.common.ClothesCardComponent
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray400
import com.konkuk.codion.ui.theme.Gray700

@Composable
fun ClothesCardList(
    title: String,
    clothesDataList: List<ClosetResponse>,
    isEditable: Boolean = false,
) {
    val state = rememberLazyListState()

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = title,
                style = CodiOnTypography.pretendard_700_16,
                color = Gray700,
            )
            if (isEditable) {
                Text(
                    text = stringResource(R.string.edit),
                    style = CodiOnTypography.pretendard_600_12.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    color = Gray400,
                    // TODO: 클릭 시 화면 이동
                )
            }

        }


        LazyRow(
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth(),
            state = state,
            contentPadding = PaddingValues(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(clothesDataList.size) { index ->
                ClothesCardComponent(clothesDataList[index])
            }
        }
    }

}