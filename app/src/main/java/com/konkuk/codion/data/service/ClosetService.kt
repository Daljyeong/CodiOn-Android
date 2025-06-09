package com.konkuk.codion.data.service

import com.konkuk.codion.data.dto.base.BaseResponse
import com.konkuk.codion.data.dto.response.ClosetResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ClosetService {
    @GET("/closet")
    suspend fun getCloset(
        @Query("category") category: String? = null,
        @Query("personalColor") personalColor: String? = null,
        @Query("color") color: String? = null,
        @Query("situationKeywords") situationKeywords: List<String>? = null,
        @Query("isFavorite") isFavorite: Boolean? = null,
        @Query("isWearableWhenRainy") isWearableWhenRainy: Boolean? = null,
    ): BaseResponse<List<ClosetResponse>>
}