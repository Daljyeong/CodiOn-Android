package com.konkuk.codion.data.repository

import com.konkuk.codion.data.dto.base.handleBaseResponse
import com.konkuk.codion.data.dto.request.CommentRequest
import com.konkuk.codion.data.dto.request.CoordinationRequest
import com.konkuk.codion.data.service.CoordinationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoordinationRepository @Inject constructor(
    private val coordinationService: CoordinationService
) {
    
    // 코디 기록 API
    suspend fun getCoordination(date: String) = runCatching {
        coordinationService.getCoordination(date).handleBaseResponse().getOrThrow()
    }
    
    suspend fun postCoordination(request: CoordinationRequest) = runCatching {
        coordinationService.postCoordination(request).handleBaseResponse().getOrThrow()
    }
    
    suspend fun patchCoordination(request: CoordinationRequest) = runCatching {
        coordinationService.patchCoordination(request).handleBaseResponse().getOrThrow()
    }
    
    suspend fun deleteCoordination(date: String) = runCatching {
        coordinationService.deleteCoordination(date).handleBaseResponse().getOrThrow()
    }
    
    // 코디 코멘트 API
    suspend fun postCoordinationComment(request: CommentRequest) = runCatching {
        coordinationService.postCoordinationComment(request).handleBaseResponse().getOrThrow()
    }
    
    suspend fun patchCoordinationComment(request: CommentRequest) = runCatching {
        coordinationService.patchCoordinationComment(request).handleBaseResponse().getOrThrow()
    }
    
    suspend fun deleteCoordinationComment(date: String) = runCatching {
        coordinationService.deleteCoordinationComment(date).handleBaseResponse().getOrThrow()
    }
} 