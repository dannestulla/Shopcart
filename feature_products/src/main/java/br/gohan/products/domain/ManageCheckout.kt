package br.gohan.products.domain

import br.gohan.products.data.ProductsRepository
import br.gohan.products.presenter.ProductsState

class ManageCheckout(
    val repository: ProductsRepository
) {

    suspend fun saveProduct(product: ProductsState) {
        repository.saveProduct(product.toProductsEntity())
    }

    suspend fun deleteAll() = repository.deleteAll()
}