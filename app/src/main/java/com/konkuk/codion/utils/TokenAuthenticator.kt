package com.konkuk.codion.utils

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.konkuk.codion.BuildConfig
import com.konkuk.codion.data.dto.request.RefreshTokenRequest
import com.konkuk.codion.data.dto.response.TokenReissueResponse
import com.konkuk.codion.data.service.AuthService
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
): Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? = runBlocking {
        val refreshToken = tokenManager.getRefreshToken().first()
        if (refreshToken.isNullOrEmpty()) return@runBlocking null

        val newToken = getNewToken(refreshToken)
        if (newToken == null) {
            tokenManager.clearToken()
            return@runBlocking null
        }

        tokenManager.saveAccessToken(newToken.accessToken)
        return@runBlocking response.request.newBuilder()
            .header("Authorization", "Bearer ${newToken.accessToken}")
            .build()
    }

    private suspend fun getNewToken(refreshToken: String): TokenReissueResponse? {
        val json = Json {
            ignoreUnknownKeys = true
            isLenient = true
            encodeDefaults = true
            explicitNulls = false
        }

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()

        val service = retrofit.create(AuthService::class.java)
        return service.postRefreshToken(RefreshTokenRequest(refreshToken)).data
    }

}