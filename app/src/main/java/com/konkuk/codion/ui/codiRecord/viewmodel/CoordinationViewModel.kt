package com.konkuk.codion.ui.codiRecord.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.codion.data.dto.request.CommentRequest
import com.konkuk.codion.data.dto.request.CoordinationRequest
import com.konkuk.codion.data.dto.response.CoordinationResponse
import com.konkuk.codion.data.repository.CoordinationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoordinationViewModel @Inject constructor(
    private val coordinationRepository: CoordinationRepository
) : ViewModel() {
    
    // 코디 조회 상태
    private val _coordination = MutableStateFlow<CoordinationResponse?>(null)
    val coordination = _coordination.asStateFlow()
    
    // 로딩 상태
    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()
    
    // 에러 상태
    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()
    
    // 성공 상태 (등록/수정/삭제 성공시)
    private val _isSuccess = MutableStateFlow(false)
    val isSuccess = _isSuccess.asStateFlow()
    
    /**
     * 특정 날짜의 코디 조회
     */
    fun getCoordination(date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            
            coordinationRepository.getCoordination(date).fold(
                onSuccess = { response ->
                    _coordination.value = response
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코디 조회 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 코디 등록
     */
    fun postCoordination(date: String, clothesIds: List<Int>) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isSuccess.value = false
            
            val request = CoordinationRequest(date = date, clothesIds = clothesIds)
            
            coordinationRepository.postCoordination(request).fold(
                onSuccess = {
                    _isSuccess.value = true
                    // 등록 후 해당 날짜 데이터 다시 조회
                    getCoordination(date)
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코디 등록 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 코디 수정
     */
    fun patchCoordination(date: String, clothesIds: List<Int>) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isSuccess.value = false
            
            val request = CoordinationRequest(date = date, clothesIds = clothesIds)
            
            coordinationRepository.patchCoordination(request).fold(
                onSuccess = {
                    _isSuccess.value = true
                    // 수정 후 해당 날짜 데이터 다시 조회
                    getCoordination(date)
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코디 수정 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 코디 삭제
     */
    fun deleteCoordination(date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isSuccess.value = false
            
            coordinationRepository.deleteCoordination(date).fold(
                onSuccess = {
                    _isSuccess.value = true
                    _coordination.value = null
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코디 삭제 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 코디 코멘트 등록
     */
    fun postCoordinationComment(date: String, mood: String, content: String? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isSuccess.value = false
            
            val request = CommentRequest(date = date, mood = mood, content = content)
            
            coordinationRepository.postCoordinationComment(request).fold(
                onSuccess = {
                    _isSuccess.value = true
                    // 코멘트 등록 후 해당 날짜 데이터 다시 조회
                    getCoordination(date)
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코멘트 등록 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 코디 코멘트 수정
     */
    fun patchCoordinationComment(date: String, mood: String, content: String? = null) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isSuccess.value = false
            
            val request = CommentRequest(date = date, mood = mood, content = content)
            
            coordinationRepository.patchCoordinationComment(request).fold(
                onSuccess = {
                    _isSuccess.value = true
                    // 코멘트 수정 후 해당 날짜 데이터 다시 조회
                    getCoordination(date)
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코멘트 수정 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 코디 코멘트 삭제
     */
    fun deleteCoordinationComment(date: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _error.value = null
            _isSuccess.value = false
            
            coordinationRepository.deleteCoordinationComment(date).fold(
                onSuccess = {
                    _isSuccess.value = true
                    // 코멘트 삭제 후 해당 날짜 데이터 다시 조회
                    getCoordination(date)
                },
                onFailure = { throwable ->
                    _error.value = throwable.message ?: "코멘트 삭제 중 오류가 발생했습니다."
                }
            )
            _isLoading.value = false
        }
    }
    
    /**
     * 에러 상태 초기화
     */
    fun clearError() {
        _error.value = null
    }
    
    /**
     * 성공 상태 초기화
     */
    fun clearSuccess() {
        _isSuccess.value = false
    }
} 