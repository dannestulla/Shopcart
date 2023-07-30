package br.gohan.products.data

import br.gohan.core.database.products.ProductsDao
import br.gohan.core.database.products.ProductsEntity
import br.gohan.products.data.remote.ProductsApi
import br.gohan.products.data.remote.ProductsData
import retrofit2.Response

class ProductsRepository(
    private val api: ProductsApi,
    private val dao: ProductsDao
) {
    suspend fun getProducts() : Response<List<ProductsData>> = api.getProducts()

    suspend fun saveProduct(products: ProductsEntity) = dao.insertAll(products)

    suspend fun deleteAll() = dao.deleteAll()

}