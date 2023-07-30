package br.gohan.products.domain

import br.gohan.core.database.products.ProductsEntity
import br.gohan.products.data.remote.ProductsData
import br.gohan.products.presenter.ProductsState

fun List<ProductsData>.toProductsState() : List<ProductsState> =
    this.map {
        ProductsState(
            name = it.title,
            price = it.price,
            description = it.description,
            id = it.id,
            image = it.image,
        )
    }

fun ProductsState.toProductsEntity() : ProductsEntity =
    ProductsEntity(
        uid = this.id,
        firstName = this.name,
        price = this.price,
        quantity = 0,
        description = this.description,
        image = this.image)

