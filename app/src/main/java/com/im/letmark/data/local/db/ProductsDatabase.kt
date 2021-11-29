package com.im.letmark.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.im.letmark.data.local.cart.CartDao
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.data.local.order.OrderDao
import com.im.letmark.data.local.order.OrderEntity
import com.im.letmark.data.local.products.ProductDao
import com.im.letmark.data.local.products.ProductEntity

@Database(
    entities = [ProductEntity::class, CartEntity::class, OrderEntity::class],
    version = 12,
    exportSchema = false
)
abstract class ProductsDatabase : RoomDatabase(){


    abstract fun getProductDao(): ProductDao
    abstract fun getCartDao(): CartDao
    abstract fun getOrderDao(): OrderDao

    companion object {

        val Table_Name= "products_table"
    }



}