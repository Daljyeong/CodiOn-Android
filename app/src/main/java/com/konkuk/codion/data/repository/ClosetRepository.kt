package com.konkuk.codion.data.repository

import com.konkuk.codion.data.dto.base.handleBaseResponse
import com.konkuk.codion.data.service.ClosetService
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ClosetRepository @Inject constructor(
     private val closetService: ClosetService
) {
    suspend fun getCloset(
        category: String? = null,
        personalColor: String? = null,
        color: String? = null,
        situationKeywords: List<String>? = null,
        isFavorite: Boolean? = null,
        isWearableWhenRainy: Boolean? = null
    ) = runCatching {
        closetService.getCloset(
            category,
            personalColor,
            color,
            situationKeywords,
            isFavorite,
            isWearableWhenRainy
        ).handleBaseResponse().getOrThrow()
    }
}