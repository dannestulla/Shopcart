package br.gohan.products.data

import retrofit2.Response

class ProductsRepository(
    private val api: ProductsApi
) {
    suspend fun getProducts() : Response<List<ProductsData>> = api.getProducts()

}