package com.konkuk.codion.ui.mypage.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.codion.data.dto.response.MyPageResponse
import com.konkuk.codion.data.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val myPageRepository: MyPageRepository
) : ViewModel() {
    private val _mypage = MutableStateFlow(MyPageResponse())
    val mypage = _mypage.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        getMyPage()
    }

    fun getMyPage() {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null

            myPageRepository.getMyPage().fold(
                onSuccess = { response ->
                    if (response != null) {
                        _mypage.value = response
                    }
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "마이페이지를 불러오는 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
}