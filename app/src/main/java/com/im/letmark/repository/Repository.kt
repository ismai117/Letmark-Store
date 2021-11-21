package com.im.letmark.repository

import androidx.room.withTransaction
import com.im.letmark.data.local.CacheMapper

import com.im.letmark.data.local.ProductDao
import com.im.letmark.data.local.ProductsDatabase
import com.im.letmark.data.network.ProductService
import com.im.letmark.data.network.ResponseMapper

import com.im.letmark.domain.model.Product
import com.im.letmark.util.Resource
import com.im.letmark.util.networkBoundResource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject


class Repository
@Inject
constructor(
    private val productService: ProductService,
    private val productDao: ProductDao,
    private val productsDatabase: ProductsDatabase,
    private val responseMapper: ResponseMapper,
    private val cacheMapper: CacheMapper
) {



    fun getProducts(): Flow<Resource<List<Product>>> {

        return networkBoundResource(
            query = {
                cacheMapper.mapfromEntityFlow(productDao.getAllProducts())
            },
            fetch = {
                responseMapper.mapFromEntityList(productService.getProducts())
            },
            saveFetchResult = { items ->
                productsDatabase.withTransaction {
                    productDao.deleteAllProducts()

                    items.forEach {
                        productDao.insert(cacheMapper.mapToEntity(it))
                    }

                }
            }
        )

    }


}