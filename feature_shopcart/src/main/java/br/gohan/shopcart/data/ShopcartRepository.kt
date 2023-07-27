package br.gohan.shopcart.data

import retrofit2.Response


class ShopcartRepository(
    private val api: ShopcartApi
) {
    suspend fun getShopcart() : Response<List<ShopcartData>> = api.getShopcart()
}