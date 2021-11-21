package com.im.letmark.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ProductEntity::class],
    version = 1,
)
abstract class ProductsDatabase : RoomDatabase(){


    abstract fun getProductDao(): ProductDao


    companion object {

        val TABLE_NAME = "products_table"

    }



}