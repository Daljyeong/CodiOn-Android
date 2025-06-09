package com.konkuk.codion.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class ClosetResponse(
    val clothesId: Int,
    val name: String,
    val imageUrl: String,
    val personalColor: String,
    val color: String,
    val situationKeywords: List<String> = emptyList(),
    val wearCount: Int,
    val category: String,
    val subCategory: String,
    val wearableWhenRainy: Boolean,
    val favorite: Boolean
)
