package br.gohan.products.domain

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import br.gohan.products.data.ProductsRepository
import br.gohan.products.presenter.ProductsState

class LocalCheckout(
    val repository: ProductsRepository
) {

    suspend fun addProduct(listProducts: SnapshotStateList<ProductsState>, product: ProductsState): SnapshotStateList<ProductsState> {
        val result = listProducts.map {
            if (it.id == product.id) {
                it.copy(quantity = ++it.quantity)
            } else {
                it
            }
        }.toMutableStateList()
        val addQuantity = product.copy(quantity = ++product.quantity).toProductsEntity()
        repository.addProduct(addQuantity)
        return result
    }

    suspend fun removeProduct(removeAll: Boolean, product: ProductsState, products : SnapshotStateList<ProductsState>): SnapshotStateList<ProductsState> {
        val result = products.map {
            if (it.id == product.id) {
                if (removeAll) {
                    it.copy(quantity = 0)
                } else {
                    it.copy(quantity = --it.quantity)
                }
            } else {
                it
            }
        }.toMutableStateList()
        if (removeAll) {
            repository.removeAll(product.id)
        } else{
            repository.removeOne(product)
        }
        return result
    }

    suspend fun getProductsSelected(): List<ProductsState> = repository.getProductsSelected().toProductsState()

}