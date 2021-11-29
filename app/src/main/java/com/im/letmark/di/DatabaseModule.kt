package com.im.letmark.di

import android.content.Context
import androidx.room.Room
import com.im.letmark.data.local.cart.CartDao
import com.im.letmark.data.local.products.ProductDao
import com.im.letmark.data.local.db.ProductsDatabase
import com.im.letmark.data.local.order.OrderDao
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
            ProductsDatabase.Table_Name
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideProductDao(productsDatabase: ProductsDatabase): ProductDao {
        return productsDatabase.getProductDao()
    }

    @Singleton
    @Provides
    fun provideCartDao(productsDatabase: ProductsDatabase): CartDao {
        return productsDatabase.getCartDao()
    }

    @Singleton
    @Provides
    fun provideOrderDao(productsDatabase: ProductsDatabase): OrderDao {
        return productsDatabase.getOrderDao()
    }

}