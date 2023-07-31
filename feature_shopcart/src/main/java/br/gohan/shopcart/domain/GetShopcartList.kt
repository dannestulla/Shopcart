package br.gohan.shopcart.domain

import br.gohan.shopcart.data.ShopcartRepository
import br.gohan.shopcart.presenter.Shopcart

class GetShopcartList(
    private val repository: ShopcartRepository
) {
    suspend fun invoke() : List<Shopcart> = repository.getShopcart().toShopcart()
}