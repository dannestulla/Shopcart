package br.gohan.products.data

import br.gohan.products.presenter.Products
import retrofit2.http.GET

interface ProductsApi {
    @GET("products")
    fun getProducts(): List<Products>
}