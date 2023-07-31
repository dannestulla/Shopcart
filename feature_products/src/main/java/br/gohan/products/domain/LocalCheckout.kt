package br.gohan.products.domain

import br.gohan.products.data.ProductsRepository
import br.gohan.products.presenter.ProductsState

class LocalCheckout(
    val repository: ProductsRepository
) {

    suspend fun addProduct(product: ProductsState) {
        repository.addProduct(product.toProductsEntity())
    }

    suspend fun removeProduct(removeAll: Boolean, product: ProductsState) {
        if (removeAll) {
            repository.removeAll(product.id)
        } else{
            repository.removeOne(product)
        }
    }

    suspend fun getProductsSelected(): List<ProductsState> = repository.getProductsSelected().toProductsState()

}