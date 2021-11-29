package com.im.letmark.data.local.products

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {


    @Query("SELECT * FROM products_table")
    fun getAllProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table WHERE category = :category")
    fun getCategoryProducts(category: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table WHERE title LIKE :title")
    fun getTitleProducts(title: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table ORDER by title ASC")
    fun getAllProductsNameAsc(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table ORDER by title DESC")
    fun getAllProductsNameDesc(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table ORDER by price ASC")
    fun getAllProductsPriceAsc(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table ORDER by price DESC")
    fun getAllProductsPriceDesc(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(productEntity: ProductEntity)

    @Update
    suspend fun update(productEntity: ProductEntity)

    @Delete
    suspend fun delete(productEntity: ProductEntity)

    @Query("DELETE FROM products_table")
    fun deleteAllProducts()









}