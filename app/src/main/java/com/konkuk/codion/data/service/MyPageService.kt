package com.konkuk.codion.data.service

import com.konkuk.codion.data.dto.base.BaseResponse
import com.konkuk.codion.data.dto.response.MyPageResponse
import retrofit2.http.GET

interface MyPageService {
    @GET("/mypage")
    suspend fun getMyPage(): BaseResponse<MyPageResponse>
}