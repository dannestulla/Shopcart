package br.gohan.products.domain

import br.gohan.products.data.ProductsData
import br.gohan.products.data.ProductsRepository
import retrofit2.Response

class GetProductsList(
    private val repository: ProductsRepository
) {
    suspend fun invoke() : Response<List<ProductsData>> = repository.getProducts()
}