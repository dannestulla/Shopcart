package br.gohan.products.domain

import br.gohan.products.data.remote.ProductsData
import br.gohan.products.data.ProductsRepository
import retrofit2.Response

class GetProductsFromApi(
    private val repository: ProductsRepository
) {
    suspend fun getProductsList() : Response<List<ProductsData>> = repository.getProducts()

}