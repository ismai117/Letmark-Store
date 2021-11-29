package com.im.letmark.data.local.cart

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Query("SELECT * FROM cart_table ORDER BY id ASC")
    fun getCartItems(): Flow<List<CartEntity>>

    @Update
    suspend fun update(cartEntity: CartEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cartEntity: CartEntity)

    @Query("DELETE FROM cart_table")
    suspend fun deleteAllCart()


}