package com.konkuk.codion.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class CoordinationResponse(
    val coordinationId: Int,
    val date: String,
    val clothes: List<ClosetResponse>,
    val comment: CommentData? = null
)

@Serializable
data class CommentData(
    val mood: String,
    val content: String? = null
) 