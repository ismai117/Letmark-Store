package com.im.letmark.data.network

import retrofit2.http.GET

interface ProductService {


    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

}