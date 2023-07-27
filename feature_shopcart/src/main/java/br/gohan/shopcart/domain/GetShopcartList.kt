package br.gohan.shopcart.domain

import br.gohan.shopcart.data.ShopcartData
import br.gohan.shopcart.data.ShopcartRepository
import retrofit2.Response

class GetShopcartList(
    private val repository: ShopcartRepository
) {
    suspend fun invoke() : Response<List<ShopcartData>> = repository.getShopcart()
}