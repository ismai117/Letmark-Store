package com.im.letmark.data.local

import com.im.letmark.data.network.ProductResponse
import com.im.letmark.domain.model.Product
import com.im.letmark.domain.util.EntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CacheMapper : EntityMapper<ProductEntity, Product> {

    override fun mapFromEntity(entity: ProductEntity): Product {
        return Product(
            id = entity.id,
            title = entity.title,
            price = entity.price,
            description = entity.description,
            category = entity.category,
            image = entity.image,
            rating = Product.Rating(
                count = entity.count,
                rate = entity.rate
            )
        )
    }

    override fun mapToEntity(domainModel: Product): ProductEntity {
        return ProductEntity(
            id = domainModel.id,
            title = domainModel.title,
            price = domainModel.price,
            description = domainModel.description,
            category = domainModel.category,
            image = domainModel.image,
            count = domainModel.rating.count,
            rate = domainModel.rating.rate
        )
    }


    fun mapFromEntityList(entity: List<ProductEntity>): List<Product> {
        return entity.map { mapFromEntity(it) }
    }

    fun mapFromDomainList(entity: List<Product>): List<ProductEntity> {
        return entity.map { mapToEntity(it) }
    }

    fun mapfromEntityFlow(entity: Flow<List<ProductEntity>>): Flow<List<Product>> {
        return entity.map { mapFromEntityList(it) }
    }


}