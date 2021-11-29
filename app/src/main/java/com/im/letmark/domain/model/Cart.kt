package com.im.letmark.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Cart(

    val id: Int,

    val title: String,

    val image: String,

    val category: String,

    val price: Double,

    val quantity: Int


)
