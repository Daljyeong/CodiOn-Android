package com.konkuk.codion.data.dto.request

import kotlinx.serialization.Serializable

@Serializable
data class CoordinationRequest(
    val date: String,
    val clothesIds: List<Int>
) 