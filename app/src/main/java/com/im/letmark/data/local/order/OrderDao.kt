package com.im.letmark.data.local.order

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface OrderDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderEntity: OrderEntity)

}