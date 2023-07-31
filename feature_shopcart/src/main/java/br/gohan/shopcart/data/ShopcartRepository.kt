package br.gohan.shopcart.data

import br.gohan.core.database.products.ProductsDao
import br.gohan.core.database.products.ProductsEntity
import retrofit2.Response


class ShopcartRepository(
    private val dao: ProductsDao
) {
    suspend fun getShopcart() : List<ProductsEntity> = dao.getAll()
    suspend fun removeAll() = dao.clearTable()
}