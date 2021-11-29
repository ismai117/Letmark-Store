package com.im.letmark.domain.model

data class Order(

    val id: Int,

    val firstname: String,

    val lastname: String,

    val phone: String,

    val address: String,

    val orderID: String,

    val customerOrder: String,

    val price: Double

) {

}