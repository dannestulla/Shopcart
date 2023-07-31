package br.gohan.core.database.products

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDao {
    @Query("SELECT * FROM products")
    fun getAll(): List<ProductsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addProduct(products: ProductsEntity)

    @Query("DELETE FROM products")
    fun clearTable()

    @Query("DELETE FROM products WHERE uid = :id")
    fun delete(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun removeOne(product: ProductsEntity)
}