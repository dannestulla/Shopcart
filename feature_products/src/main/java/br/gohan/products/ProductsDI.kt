package br.gohan.products

import androidx.lifecycle.SavedStateHandle
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val productsModule = module {
    viewModel {
        ProductsViewModel()
    }
}