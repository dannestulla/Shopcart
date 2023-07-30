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
    fun insertAll(products: ProductsEntity)

    @Query("DELETE FROM products")
    fun deleteAll()
}