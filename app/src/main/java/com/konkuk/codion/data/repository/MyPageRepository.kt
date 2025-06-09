package com.konkuk.codion.data.repository

import com.konkuk.codion.data.dto.base.handleBaseResponse
import com.konkuk.codion.data.service.MyPageService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPageRepository @Inject constructor(
     private val myPageService: MyPageService
){
    suspend fun getMyPage() = runCatching {
        myPageService.getMyPage().handleBaseResponse().getOrThrow()
    }
}