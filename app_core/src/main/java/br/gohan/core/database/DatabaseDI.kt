package br.gohan.core.database

import androidx.room.Room
import br.gohan.core.database.products.ProductsDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ProductsDatabase::class.java,
            "products"
        ).build()
    }

    single {
        get<ProductsDatabase>().productsDao()
    }
}