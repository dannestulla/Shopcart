package br.gohan.core.database.products

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductsEntity::class], version = 2)
abstract class ProductsDatabase : RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}