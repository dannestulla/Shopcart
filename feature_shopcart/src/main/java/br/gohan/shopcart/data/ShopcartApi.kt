package br.gohan.shopcart.data

import retrofit2.Response
import retrofit2.http.GET

interface ShopcartApi {

    @GET("shopcart")
    suspend fun getShopcart() : Response<List<ShopcartData>>
}