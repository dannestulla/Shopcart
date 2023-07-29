package br.gohan.products.presenter

import androidx.compose.material3.SnackbarHostState
import br.gohan.core.AppEvents
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

sealed class ProductsEvents {
    data class SaveProduct(val product: ProductsState) : ProductsEvents()
    object DeleteAll : ProductsEvents()
}

fun handleEvents(
    event: ProductsEvents,
    viewModel: ProductsViewModel,
    appEvents: MutableSharedFlow<AppEvents>,
    snackbarScope: CoroutineScope,
    snackBarHost: SnackbarHostState
) {
    when (event) {
        is ProductsEvents.SaveProduct -> {
            viewModel.saveProduct(event.product)
            viewModel.showSnackbar(snackbarScope, snackBarHost)
        }
        is ProductsEvents.DeleteAll -> {
            viewModel.deleteAll()
        }
    }
}