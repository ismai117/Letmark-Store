package com.im.letmark.data.local.util

import com.im.letmark.data.local.order.OrderEntity
import com.im.letmark.domain.model.Order
import com.im.letmark.domain.util.EntityMapper

class OrderCacheMapper : EntityMapper<OrderEntity, Order> {

    override fun mapFromEntity(entity: OrderEntity): Order {
        TODO("Not yet implemented")
    }

    override fun mapToEntity(domainModel: Order): OrderEntity {
        return OrderEntity(
            id = domainModel.id,
            firstname = domainModel.firstname,
            lastname = domainModel.lastname,
            phone = domainModel.phone,
            address = domainModel.address,
            orderID = domainModel.orderID,
            customerOrder = domainModel.customerOrder,
            price = domainModel.price
        )
    }

}