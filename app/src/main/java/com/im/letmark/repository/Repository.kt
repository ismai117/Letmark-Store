package com.im.letmark.repository

import androidx.room.Query
import androidx.room.withTransaction
import com.im.letmark.data.local.cart.CartDao
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.data.local.util.ProductCacheMapper

import com.im.letmark.data.local.products.ProductDao
import com.im.letmark.data.local.db.ProductsDatabase
import com.im.letmark.data.local.order.OrderDao
import com.im.letmark.data.local.order.OrderEntity
import com.im.letmark.data.local.products.ProductEntity
import com.im.letmark.data.local.util.CartCacheMapper
import com.im.letmark.data.local.util.OrderCacheMapper
import com.im.letmark.data.network.service.ProductService
import com.im.letmark.data.network.util.ResponseMapper
import com.im.letmark.domain.model.Cart
import com.im.letmark.domain.model.Order

import com.im.letmark.domain.model.Product
import com.im.letmark.util.Resource
import com.im.letmark.util.networkBoundResource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject


class Repository
@Inject
constructor(
    private val productService: ProductService,
    private val productsDatabase: ProductsDatabase,
    private val productDao: ProductDao,
    private val cartDao: CartDao,
    private val orderDao: OrderDao,
    private val responseMapper: ResponseMapper,
    private val cacheMapper: ProductCacheMapper,
    private val cartCacheMapper: CartCacheMapper,
    private val orderCacheMapper: OrderCacheMapper
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


    fun getAllProductsNameAsc(): Flow<List<Product>> {
        return cacheMapper.mapfromEntityFlow(productDao.getAllProductsNameAsc())
    }

    fun getAllProductsNameDesc(): Flow<List<Product>> {
        return cacheMapper.mapfromEntityFlow(productDao.getAllProductsNameDesc())
    }

    fun getAllProductsPriceAsc(): Flow<List<Product>> {
        return cacheMapper.mapfromEntityFlow(productDao.getAllProductsPriceAsc())
    }

    fun getAllProductsPriceDesc(): Flow<List<Product>> {
        return cacheMapper.mapfromEntityFlow(productDao.getAllProductsPriceDesc())
    }

    fun getCategoryProducts(category: String): Flow<List<Product>> {
        return cacheMapper.mapfromEntityFlow(productDao.getCategoryProducts(category))
    }

    fun getTitleProducts(title: String): Flow<List<Product>> {
        return cacheMapper.mapfromEntityFlow(productDao.getTitleProducts(title))
    }

    suspend fun insertItem(cartEntity: CartEntity) {
        cartDao.insert(cartEntity)
    }

    suspend fun deleteAllCart() {
        cartDao.deleteAllCart()
    }

    fun cartItems(): Flow<List<Cart>> {
        return cartCacheMapper.mapfromEntityFlow(cartDao.getCartItems())
    }

    suspend fun updateCartItem(cartEntity: CartEntity){
        cartDao.update(cartEntity)
    }
    
    suspend fun saveCustomerOrder(order: Order){

        productsDatabase.withTransaction {

            orderDao.insert(orderCacheMapper.mapToEntity(order))
            cartDao.deleteAllCart()

        }

    }

}