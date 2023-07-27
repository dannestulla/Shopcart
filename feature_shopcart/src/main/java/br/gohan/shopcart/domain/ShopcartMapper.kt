package br.gohan.shopcart.domain

import br.gohan.shopcart.data.ShopcartData
import br.gohan.shopcart.presenter.ShopcartState

fun List<ShopcartData>.toShopcartState() : List<ShopcartState> =
    this.map {
        ShopcartState(
            name = it.name,
            price = it.price,
            description = it.description,
            id = it.id,
            image = it.image
        )
    }



