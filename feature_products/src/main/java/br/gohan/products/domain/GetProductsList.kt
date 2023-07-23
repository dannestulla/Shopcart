package br.gohan.products.domain

import br.gohan.products.data.ProductsRepository

class GetProductsList(
    private val repository: ProductsRepository
) {
    fun invoke() = repository.getProducts()
}