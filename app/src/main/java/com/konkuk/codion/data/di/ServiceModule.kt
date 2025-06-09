package com.konkuk.codion.data.di

import com.konkuk.codion.data.service.AuthService
import com.konkuk.codion.data.service.ClosetService
import com.konkuk.codion.data.service.CoordinationService
import com.konkuk.codion.data.service.MyPageService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesClosetService(retrofit: Retrofit): ClosetService =
        retrofit.create(ClosetService::class.java)

    @Provides
    @Singleton
    fun providesMyPageService(retrofit: Retrofit): MyPageService =
        retrofit.create(MyPageService::class.java)

    @Provides
    @Singleton
    fun providesCoordinationService(retrofit: Retrofit): CoordinationService =
        retrofit.create(CoordinationService::class.java)
}