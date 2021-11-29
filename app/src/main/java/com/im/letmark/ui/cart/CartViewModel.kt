package com.im.letmark.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CartViewModel
@Inject
constructor(
    private val repository: Repository
) : ViewModel() {


    val cartItems = repository.cartItems().asLiveData()

    fun update(cartEntity: CartEntity) = runBlocking {
        repository.updateCartItem(cartEntity)
    }

    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllCart()
    }

}