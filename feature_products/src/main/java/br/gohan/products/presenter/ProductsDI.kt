package br.gohan.products.presenter

import br.gohan.products.data.ProductsApi
import br.gohan.products.data.ProductsRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val productsModule = module {
    viewModel {
        ProductsViewModel(get())
    }

    factory {
        ProductsRepository(get())
    }

    single {
        provideRetrofit()
    }

    factory {
        get<Retrofit>().create(ProductsApi::class.java)
    }
}

private fun provideRetrofit() =
    Retrofit.Builder()
        .baseUrl("https://api.spotify.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

