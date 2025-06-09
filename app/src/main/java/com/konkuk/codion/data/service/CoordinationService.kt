package com.konkuk.codion.data.service

import com.konkuk.codion.data.dto.base.BaseResponse
import com.konkuk.codion.data.dto.request.CommentRequest
import com.konkuk.codion.data.dto.request.CoordinationRequest
import com.konkuk.codion.data.dto.response.CoordinationResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface CoordinationService {
    
    // 코디 기록 API
    @GET("/coordination")
    suspend fun getCoordination(
        @Query("date") date: String
    ): BaseResponse<CoordinationResponse>
    
    @POST("/coordination")
    suspend fun postCoordination(
        @Body request: CoordinationRequest
    ): BaseResponse<Unit>
    
    @PATCH("/coordination")
    suspend fun patchCoordination(
        @Body request: CoordinationRequest
    ): BaseResponse<Unit>
    
    @DELETE("/coordination")
    suspend fun deleteCoordination(
        @Query("date") date: String
    ): BaseResponse<Unit>
    
    // 코디 코멘트 API
    @POST("/coordination/comment")
    suspend fun postCoordinationComment(
        @Body request: CommentRequest
    ): BaseResponse<Unit>
    
    @PATCH("/coordination/comment")
    suspend fun patchCoordinationComment(
        @Body request: CommentRequest
    ): BaseResponse<Unit>
    
    @DELETE("/coordination/comment")
    suspend fun deleteCoordinationComment(
        @Query("date") date: String
    ): BaseResponse<Unit>
} 