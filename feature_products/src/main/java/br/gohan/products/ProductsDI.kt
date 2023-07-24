package br.gohan.products

import br.gohan.core.AppEvents
import br.gohan.products.data.ProductsApi
import br.gohan.products.data.ProductsRepository
import br.gohan.products.domain.GetProductsList
import br.gohan.products.presenter.ProductsViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val productsModule = module {
    single {
        MutableSharedFlow<AppEvents>()
    }

    viewModel {
        ProductsViewModel(get(), get(), get())
    }

    factory {
        GetProductsList(get())
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
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

