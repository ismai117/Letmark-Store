package com.im.letmark.data.local


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.im.junitpractise.getOrAwaitValue
import com.im.letmark.data.local.db.ProductsDatabase
import com.im.letmark.data.local.products.ProductDao
import com.im.letmark.data.local.products.ProductEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DatabaseTest {

    @get: Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var productsDatabase: ProductsDatabase
    private lateinit var productDao: ProductDao


    @Before
    fun setUp() {
        productsDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ProductsDatabase::class.java,
        ).allowMainThreadQueries().build()

        productDao = productsDatabase.getProductDao()
    }

    @After
    fun tearDown() {
        productsDatabase.close()
    }


    @Test
    fun insert() = runBlocking{

        val item = ProductEntity(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rate = 3.9,
            count = 120
        )

        productDao.insert(item)

        val items = productDao.getAllProducts().asLiveData().getOrAwaitValue()


        assertThat(items).contains(item)


    }


    @Test
    fun update() = runBlocking {


        val item = ProductEntity(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rate = 3.9,
            count = 120
        )

        productDao.insert(item)

        val updateItem = ProductEntity(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 69.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rate = 3.9,
            count = 120
        )

        productDao.update(updateItem)

        val items = productDao.getAllProducts().asLiveData().getOrAwaitValue()


        assertThat(items).contains(updateItem)

    }

    @Test
    fun delete() = runBlocking {


        val item = ProductEntity(
            id = 1,
            title = "Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops",
            price = 109.95,
            description = "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday",
            category = "men's clothing",
            image = "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
            rate = 3.9,
            count = 120
        )

        productDao.insert(item)

        productDao.delete(item)

        val items = productDao.getAllProducts().asLiveData().getOrAwaitValue()

        assertThat(items).contains(item)


    }




}


