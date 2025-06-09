package com.konkuk.codion.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.konkuk.codion.R
import com.konkuk.codion.data.dto.response.ClosetResponse
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray200
import com.konkuk.codion.ui.theme.Gray300
import com.konkuk.codion.ui.theme.Gray500
import com.konkuk.codion.ui.theme.Gray700

@Composable
fun ClothesCardComponent(
    clothesData: ClosetResponse,
    isClickable: Boolean = false,
    isSelected: Boolean = false,
    onClick: (() -> Unit)? = null
) {
    var isHeartClicked by remember { mutableStateOf(clothesData.favorite) }

    val outerPadding: Dp = if (isClickable) 12.dp else 0.dp
    val outerBackgroundColor = if (isClickable && isSelected) Gray300 else Color.Transparent
    val outerBorderColor = if (isClickable && isSelected) Gray700 else Color.Transparent


    Column(
        modifier = Modifier
            .background(outerBackgroundColor, shape = RoundedCornerShape(12.dp))
            .border(width = 1.dp, color = outerBorderColor, shape = RoundedCornerShape(12.dp))
            .padding(outerPadding)
            .clickable(enabled = isClickable) { onClick?.invoke() } // 👉 클릭 가능 시 콜백 호출
    ) {
        Box(
            modifier = Modifier
                .size(width = 148.dp, height = 196.dp)
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Gray200)
        ) {
            AsyncImage(
                model = clothesData.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(196.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = String.format(
                        stringResource(R.string.number_of_wear),
                        clothesData.wearCount
                    ),
                    style = CodiOnTypography.pretendard_400_12,
                    color = Gray700,
                )

                Icon(
                    painter = painterResource(
                        id = if (isHeartClicked) R.drawable.ic_heart_clicked else R.drawable.ic_heart
                    ),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { isHeartClicked = !isHeartClicked }
                )
            }
        }

        Text(
            text = clothesData.name,
            style = CodiOnTypography.pretendard_600_16,
            color = Gray700,
            modifier = Modifier.padding(top = 12.dp)
        )

        Text(
            text = clothesData.personalColor,
            style = CodiOnTypography.pretendard_600_12,
            color = Gray500,
            modifier = Modifier.padding(top = 4.dp)
        )

        if (clothesData.situationKeywords.isNotEmpty()) {
            LazyRow(
                modifier = Modifier
                    .width(148.dp)
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(clothesData.situationKeywords.size) { index ->
                    ChipComponent(clothesData.situationKeywords[index])
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ClothesCardComponentPreview() {
//    ClothesCardComponent(ClothesCardDummyData.dummyData[0])
}