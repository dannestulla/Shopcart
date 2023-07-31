package br.gohan.shopcart.domain

import br.gohan.core.database.products.ProductsEntity
import br.gohan.shopcart.presenter.Shopcart

fun List<ProductsEntity>.toShopcart() : List<Shopcart> =
    this.map {
            Shopcart(
                name = it.name,
                price = it.price,
                quantity = it.quantity
            )
        }
