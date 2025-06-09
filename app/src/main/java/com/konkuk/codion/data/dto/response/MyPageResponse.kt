package com.konkuk.codion.data.dto.response

import kotlinx.serialization.Serializable

@Serializable
data class MyPageResponse(
    val email: String = "",
    val nickname: String = "",
    val personalColor: String = "",
)
