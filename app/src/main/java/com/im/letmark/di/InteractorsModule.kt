package com.im.letmark.di

import com.im.letmark.data.local.util.CartCacheMapper
import com.im.letmark.data.local.util.OrderCacheMapper
import com.im.letmark.data.local.util.ProductCacheMapper
import com.im.letmark.data.network.util.ResponseMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideCacheMapper(): ProductCacheMapper {
        return ProductCacheMapper()
    }

    @Singleton
    @Provides
    fun provideCartCacheMapper(): CartCacheMapper {
        return CartCacheMapper()
    }

    @Singleton
    @Provides
    fun provideOrderCacheMapper(): OrderCacheMapper{
        return OrderCacheMapper()
    }

}