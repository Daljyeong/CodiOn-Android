package com.konkuk.codion.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class TokenReissueResponse(
    val accessToken: String,
)
