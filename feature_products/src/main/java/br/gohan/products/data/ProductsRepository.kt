package br.gohan.products.data

import br.gohan.core.database.products.ProductsDao
import br.gohan.core.database.products.ProductsEntity
import br.gohan.products.data.remote.ProductsApi
import br.gohan.products.data.remote.ProductsData
import br.gohan.products.domain.toProductsEntity
import br.gohan.products.presenter.ProductsState
import retrofit2.Response

class ProductsRepository(
    private val api: ProductsApi,
    private val dao: ProductsDao
) {
    suspend fun getProducts() : Response<List<ProductsData>> = api.getProducts()

    suspend fun addProduct(products: ProductsEntity) = dao.addProduct(products)

    suspend fun removeAll(id: Int) = dao.delete(id)

    fun removeOne(product: ProductsState) = dao.removeOne(product.toProductsEntity())

    fun getProductsSelected(): List<ProductsEntity> = dao.getAll()

}