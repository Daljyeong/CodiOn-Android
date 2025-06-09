package com.konkuk.codion.ui.myCloset.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.codion.data.dto.response.ClosetResponse
import com.konkuk.codion.data.repository.ClosetRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyClosetViewModel @Inject constructor(
    private val closetRepository: ClosetRepository
) : ViewModel() {

    private val _closet = MutableStateFlow<List<ClosetResponse>>(emptyList())
    val closet = _closet.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getCloset()
    }

    fun getCloset(
        category: String? = null,
        personalColor: String? = null,
        color: String? = null,
        situationKeywords: List<String>? = null,
        isFavorite: Boolean? = null,
        isWearableWhenRainy: Boolean? = null
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            closetRepository.getCloset(
                category,
                personalColor,
                color,
                situationKeywords,
                isFavorite,
                isWearableWhenRainy
            ).fold(
                onSuccess = { response ->
                    if (response != null) {
                        _closet.value = response
                    }
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "옷장을 불러오는 중 오류가 발생했습니다."
                }
            )

            _isLoading.value = false
        }
    }
}