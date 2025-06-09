package com.konkuk.codion.data.repository

import com.konkuk.codion.data.dto.base.handleBaseResponse
import com.konkuk.codion.data.dto.request.LoginRequest
import com.konkuk.codion.data.service.AuthService
import com.konkuk.codion.utils.TokenManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) {
    suspend fun emailLogin(
        email: String,
        password: String
    ) = runCatching {
        val response = authService.postLogin(
            LoginRequest(
                email = email,
                password = password
            )
        ).handleBaseResponse().getOrThrow()

        response?.let {
            tokenManager.saveAccessToken(it.accessToken)
            tokenManager.saveRefreshToken(it.refreshToken)
        }
    }
}