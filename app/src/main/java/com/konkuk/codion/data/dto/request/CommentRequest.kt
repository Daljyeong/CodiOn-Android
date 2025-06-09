package com.konkuk.codion.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CommentRequest(
    val date: String,
    val mood: String,
    val content: String? = null,
)
