package br.gohan.core.database.products

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class ProductsEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "quantity") val quantity: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "image") val image: String?
)