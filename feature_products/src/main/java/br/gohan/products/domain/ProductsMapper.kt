package br.gohan.products.domain

import br.gohan.core.database.products.ProductsEntity
import br.gohan.products.data.remote.ProductsData
import br.gohan.products.presenter.ProductsState

fun List<ProductsData>.toProductState() : List<ProductsState> =
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
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        description = this.description,
        image = this.image
    )

fun List<ProductsEntity>.toProductsState() : List<ProductsState> =
    this.map {
        ProductsState(
            name = it.name,
            price = it.price,
            description = it.description,
            id = it.uid,
            image = it.image,
            quantity = it.quantity
        )
    }

