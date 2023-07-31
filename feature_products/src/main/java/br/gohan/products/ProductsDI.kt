package br.gohan.products

import android.content.SharedPreferences
import br.gohan.core.AppEvents
import br.gohan.products.data.ProductsRepository
import br.gohan.products.data.remote.ProductsApi
import br.gohan.products.domain.GetProductsFromApi
import br.gohan.products.domain.LocalCheckout
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
        GetProductsFromApi(get())
    }

    factory {
        LocalCheckout(get())
    }

    factory {
        ProductsRepository(
            api = get(),
            dao = get())
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ProductsApi::class.java)
    }
}
