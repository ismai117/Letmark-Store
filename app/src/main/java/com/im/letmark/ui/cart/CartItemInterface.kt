package com.im.letmark.ui.cart

import com.im.letmark.data.local.cart.CartEntity

interface CartItemInterface {

    fun updateItem(cartEntity: CartEntity)

    fun deleteItem(cartEntity: CartEntity)

}