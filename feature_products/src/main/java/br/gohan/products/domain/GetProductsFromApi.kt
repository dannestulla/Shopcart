package br.gohan.products.domain

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import br.gohan.core.AppEvents
import br.gohan.products.data.remote.ProductsData
import br.gohan.products.data.ProductsRepository
import br.gohan.products.presenter.ProductsState
import retrofit2.Response

class GetProductsFromApi(
    private val repository: ProductsRepository
) {
    suspend fun getProductsList(productsSelected: List<ProductsState>): List<ProductsState>? {
        val response = repository.getProducts()
        return if (response.isSuccessful) {
            val body = response.body()?.toProductState()
            body?.map { product ->
                product.isLoading = false
                productsSelected.forEach { productSelected ->
                    if (product.id == productSelected.id) {
                        product.quantity = productSelected.quantity
                    }
                }
            }
            body ?: mutableListOf()
        } else {
           null
        }
    }

}