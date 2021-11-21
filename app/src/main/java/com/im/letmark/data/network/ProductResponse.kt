package com.im.letmark.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class ProductResponse(

    @Json(name = "id")
    val id: Int,

    @Json(name = "title")
    val title: String,

    @Json(name = "price")
    val price: Double,

    @Json(name = "description")
    val description: String,

    @Json(name = "category")
    val category: String,

    @Json(name = "image")
    val image: String,

    @Json(name = "rating")
    val rating: ItemRating,



){

    @JsonClass(generateAdapter = true)

    data class ItemRating(
        @Json(name = "rate")
        val rate: Double,

        @Json(name = "count")
        val count: Int
    )

}