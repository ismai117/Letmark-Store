package com.im.letmark.di

import com.im.letmark.data.local.CacheMapper
import com.im.letmark.data.network.ResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideResponseMapper(): ResponseMapper {
        return ResponseMapper()
    }

    @Singleton
    @Provides
    fun provideCacheMapper(): CacheMapper {
        return CacheMapper()
    }

}