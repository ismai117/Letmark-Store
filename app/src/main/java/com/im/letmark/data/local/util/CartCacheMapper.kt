package com.im.letmark.data.local.util

import com.im.letmark.data.local.cart.CartEntity
import com.im.letmark.domain.model.Cart
import com.im.letmark.domain.util.EntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.w3c.dom.Entity

class CartCacheMapper : EntityMapper<CartEntity, Cart> {

    override fun mapFromEntity(entity: CartEntity): Cart {
        return Cart(
            id = entity.id,
            title = entity.title,
            image = entity.image,
            category = entity.category,
            price = entity.price,
            quantity = entity.quantity
        )
    }

    override fun mapToEntity(domainModel: Cart): CartEntity {
        TODO("Not yet implemented")
    }


    fun mapFromEntityList(entity: List<CartEntity>): List<Cart> {
        return entity.map { mapFromEntity(it) }
    }

    fun mapfromEntityFlow(entity: Flow<List<CartEntity>>): Flow<List<Cart>> {
        return entity.map { mapFromEntityList(it) }
    }

}