package com.im.letmark.data.network.service

import com.im.letmark.data.network.products.ProductResponse
import retrofit2.http.GET

interface ProductService {


    @GET("products")
    suspend fun getProducts(): List<ProductResponse>

}