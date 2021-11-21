package com.im.letmark.di

import android.content.Context
import androidx.room.Room
import com.im.letmark.data.local.ProductDao
import com.im.letmark.data.local.ProductsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ProductsDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductsDatabase::class.java,
            ProductsDatabase.TABLE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideProductDao(productsDatabase: ProductsDatabase): ProductDao {
        return productsDatabase.getProductDao()
    }


}