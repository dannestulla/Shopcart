package br.gohan.shopcart

import br.gohan.core.AppEvents
import br.gohan.shopcart.data.ShopcartApi
import br.gohan.shopcart.data.ShopcartRepository
import br.gohan.shopcart.domain.GetShopcartList
import br.gohan.shopcart.presenter.ShopcartViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

val shopcartModule = module {
    single {
        MutableSharedFlow<AppEvents>()
    }

    viewModel {
        ShopcartViewModel(get(), get())
    }

    factory {
        GetShopcartList(get())
    }

    factory {
        ShopcartRepository(get())
    }

    single {
        provideRetrofit()
    }

    factory {
        get<Retrofit>().create(ShopcartApi::class.java)
    }
}

private fun provideRetrofit() =
    Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()