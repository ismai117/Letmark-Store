package com.im.letmark.data.network

import com.im.letmark.domain.model.Product
import com.im.letmark.domain.util.EntityMapper

class ResponseMapper : EntityMapper<ProductResponse, Product> {

    override fun mapFromEntity(entity: ProductResponse): Product {
        return Product(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            description = entity.description,
            category = entity.category,
            image = entity.image,
            rating = Product.Rating(
                count = entity.rating.count,
                rate = entity.rating.rate
            )
        )
    }

    override fun mapToEntity(domainModel: Product): ProductResponse {
        TODO("Not yet implemented")
    }


    fun mapFromEntityList(entity: List<ProductResponse>): List<Product> {
        return entity.map { mapFromEntity(it) }
    }

}