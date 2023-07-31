package br.gohan.products.presenter

import androidx.compose.material3.SnackbarHostState
import br.gohan.core.AppEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.CoroutineScope

sealed class ProductsEvents {
    data class AddProduct(val product: ProductsState) : ProductsEvents()
    data class RemoveProduct(val product: ProductsState, val removeAll: Boolean = false) : ProductsEvents()
}

fun handleEvents(
    event: ProductsEvents,
    viewModel: ProductsViewModel,
    appEvents: MutableSharedFlow<AppEvents>,
    snackbarScope: CoroutineScope,
    snackBarHost: SnackbarHostState
) {
    when (event) {
        is ProductsEvents.AddProduct -> {
            viewModel.addProduct(event.product)
            viewModel.showSnackbar(snackbarScope, snackBarHost, event.product.name)
        }
        is ProductsEvents.RemoveProduct -> {
            viewModel.removeProduct(event.removeAll, event.product)
        }
    }
}