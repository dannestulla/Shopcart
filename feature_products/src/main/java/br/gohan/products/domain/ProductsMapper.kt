package br.gohan.products.domain

import br.gohan.products.data.ProductsData
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
