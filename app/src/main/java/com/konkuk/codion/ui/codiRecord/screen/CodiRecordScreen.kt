package com.konkuk.codion.ui.codiRecord.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.codion.R
import com.konkuk.codion.ui.codiRecord.component.CodiRecordCalendar
import com.konkuk.codion.ui.codiRecord.component.CodiRecordEmpty
import com.konkuk.codion.ui.codiRecord.component.MyComment
import com.konkuk.codion.ui.codiRecord.dummy.CodiRecordDummyData
import com.konkuk.codion.ui.common.TopAppBarState
import com.konkuk.codion.ui.home.component.ClothesCardList
import com.konkuk.codion.ui.myCloset.viewmodel.MyClosetViewModel
import java.time.LocalDate

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CodiRecordScreen(
    padding: PaddingValues,
    onAddClick: () -> Unit,
    setTopAppBar: (TopAppBarState?) -> Unit,
    viewModel: MyClosetViewModel = hiltViewModel()
) {
    val myCloset by viewModel.closet.collectAsStateWithLifecycle()

    val codiRecordMap = CodiRecordDummyData.dummyData.associateBy { it.date }

    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    val selectedReview = codiRecordMap[selectedDate]

    LaunchedEffect(Unit) {
        setTopAppBar(
            TopAppBarState(
                titleId = R.string.codi_record
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        item {
            CodiRecordCalendar(
                recordData = codiRecordMap.mapValues { it.value.emotion },
                onDateSelected = { selectedDate = it }
            )
        }

        item {
            selectedReview?.let {
                Column {
                    ClothesCardList(
                        title = stringResource(
                            R.string.wore_clothes,
                            selectedDate.month.value,
                            selectedDate.dayOfMonth
                        ),
                        clothesDataList = myCloset.filterIndexed { index, _ -> index == 0 || index == 3 }, // 🔥 여기가 핵심!
                        isEditable = true
                    )

                    Spacer(Modifier.height(24.dp))

                    MyComment(record = it)
                }
            } ?: CodiRecordEmpty(
                onAddClick = onAddClick,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun CodiRecordScreenPreview() {
    CodiRecordScreen(
        padding = PaddingValues(0.dp),
        onAddClick = {},
        setTopAppBar = {}
    )
}