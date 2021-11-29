package com.im.letmark.data.local.order

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OrderEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int,

    val firstname: String,

    val lastname: String,

    val phone: String,

    val address: String,

    val orderID: String,

    val customerOrder: String,

    val price: Double

){


}
