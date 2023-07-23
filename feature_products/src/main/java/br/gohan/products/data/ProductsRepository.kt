package br.gohan.products.data

class ProductsRepository(
    private val api: ProductsApi
) {
    fun getProducts(): Any = api.getProducts()
}