package com.konkuk.codion.ui.myCloset.screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.konkuk.codion.R
import com.konkuk.codion.ui.common.ClothesCardComponent
import com.konkuk.codion.ui.common.TopAppBarState
import com.konkuk.codion.ui.common.filter.FilterBottomSheetList
import com.konkuk.codion.ui.common.filter.FilterButton
import com.konkuk.codion.ui.common.filter.FilterType
import com.konkuk.codion.ui.common.filter.PersonalColorType
import com.konkuk.codion.ui.common.filter.SortDropdown
import com.konkuk.codion.ui.common.filter.SortType
import com.konkuk.codion.ui.myCloset.ClothesCategoryType
import com.konkuk.codion.ui.myCloset.viewmodel.MyClosetViewModel
import com.konkuk.codion.ui.theme.CodiOnTypography
import com.konkuk.codion.ui.theme.Gray700
import com.konkuk.codion.ui.theme.Gray900

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyClosetScreen(
    padding: PaddingValues,
    onAddClick: () -> Unit,
    setTopAppBar: (TopAppBarState?) -> Unit,
    viewModel: MyClosetViewModel = hiltViewModel()
) {
    val myCloset by viewModel.closet.collectAsStateWithLifecycle()

    val topLevelTabs = ClothesCategoryType.getTopLevelCategories()
    var selectedTab by remember { mutableStateOf(topLevelTabs.first()) }

// 필터 상태
    val appliedCategoryOptions = remember { mutableStateListOf<ClothesCategoryType>() }
    val appliedPersonalColorOptions = remember { mutableStateListOf<PersonalColorType>() }

    val tempCategoryOptions = remember { mutableStateListOf<ClothesCategoryType>() }
    val tempPersonalColorOptions = remember { mutableStateListOf<PersonalColorType>() }

    var expandedSheet by remember { mutableStateOf<FilterType?>(null) }

//    val allClothes = ClothesCardDummyData.dummyData

//    val filteredClothes = myCloset.filter { item ->
//        val categoryType = item.toCategoryType()
//        val personalColorType = item.toPersonalColorType()
//
//        val matchCategory =
//            appliedCategoryOptions.isEmpty() || (categoryType != null && appliedCategoryOptions.contains(categoryType))
//        val matchPersonalColor =
//            appliedPersonalColorOptions.isEmpty() || (personalColorType != null && appliedPersonalColorOptions.contains(personalColorType))
//        val matchTopTab =
//            selectedTab == ClothesCategoryType.ALL || (categoryType?.parent == selectedTab)
//
//        matchCategory && matchPersonalColor && matchTopTab
//    }

    // sort
    val options = SortType.entries
    var selectedOption by rememberSaveable { mutableStateOf(SortType.FREQUENCY) }

//    LaunchedEffect(myCloset) {
//        setTopAppBar(
//            TopAppBarState(
//                titleId = R.string.my_closet,
//                rightIconId = R.drawable.ic_add,
//                onRightClicked = {
//                    onAddClick()
//                }
//            )
//        )
//
//        viewModel.getCloset()
//        Log.d("MyClosetScreen", "myCloset: $myCloset")
//    }
    LaunchedEffect(selectedTab) {
        setTopAppBar(
            TopAppBarState(
                titleId = R.string.my_closet,
                rightIconId = R.drawable.ic_add,
                onRightClicked = {
                    onAddClick()
                }
            )
        )

        val categoryToSend = if (selectedTab == ClothesCategoryType.ALL) null else selectedTab.name

        Log.d("MyClosetScreen", "탭 전환 → category: $categoryToSend")

        viewModel.getCloset(
            category = categoryToSend,
            personalColor = appliedPersonalColorOptions.firstOrNull()?.label
        )
    }


    Column(
        modifier = Modifier
            .padding(padding)
            .fillMaxSize()
    ) {
        ScrollableTabRow(
            selectedTabIndex = topLevelTabs.indexOf(selectedTab),
            edgePadding = 0.dp,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[topLevelTabs.indexOf(selectedTab)]),
                    color = Gray900
                )

            },
        ) {
            topLevelTabs.forEachIndexed { index, tab ->
                val isSelected = selectedTab == tab
                Tab(
                    selected = isSelected,
                    onClick = { selectedTab = tab },
                    text = {
                        Text(
                            text = tab.label,
                            style = if (isSelected) CodiOnTypography.pretendard_600_16 else CodiOnTypography.pretendard_400_16,
                            color = if (isSelected) Gray900 else Gray700
                        )
                    },
                    selectedContentColor = Gray700,
                )
            }
        }

        FilterBottomSheetList(
            selectedParentCategory = selectedTab,
            selectedCategoryOptions = tempCategoryOptions,
            selectedPersonalColorOptions = tempPersonalColorOptions,
            onClick = {
                appliedCategoryOptions.clear()
                appliedCategoryOptions.addAll(tempCategoryOptions)

                appliedPersonalColorOptions.clear()
                appliedPersonalColorOptions.addAll(tempPersonalColorOptions)

                expandedSheet = null

                Log.d("필터", "보낼 category: ${selectedTab.name}, personalColor: ${appliedPersonalColorOptions.firstOrNull()?.label}")

                viewModel.getCloset(
                    category = selectedTab.name,  // ✅ 여기! 항상 대분류 값만 보냄
                    personalColor = appliedPersonalColorOptions.firstOrNull()?.label,
                    // 추후 color, situationKeywords 등 추가 가능
                )
            },
            expandedSheet = expandedSheet,
            onDismiss = { expandedSheet = null },
            onOpenSheet = { expandedSheet = it }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // 적용된 필터 목록
            LazyRow(
                modifier = Modifier
                    .width(256.dp),
            ) {
                items(appliedCategoryOptions) { category ->
                    FilterButton(
                        filterLabel = category.label,
                        onClose = {
                            appliedCategoryOptions.remove(category)
                            tempCategoryOptions.remove(category)
                        }
                    )
                }
                items(appliedPersonalColorOptions) { color ->
                    FilterButton(
                        filterLabel = color.label,
                        onClose = {
                            appliedPersonalColorOptions.remove(color)
                            tempPersonalColorOptions.remove(color)
                        }
                    )
                }
            }

            SortDropdown(
                options = options.map { it.label },
                selectedOption = selectedOption.label,
            ) { selectedLabel ->
                selectedOption = options.first { it.label == selectedLabel }
            }
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = 20.dp),
        ) {
            items(myCloset) { item ->
                ClothesCardComponent(clothesData = item, isClickable = true, isSelected = false)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MyClosetScreenPreview() {
    MyClosetScreen(
        padding = PaddingValues(0.dp),
        onAddClick = {},
        setTopAppBar = {}
    )
}