package br.gohan.products.presenter

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.core.AppEvents
import br.gohan.products.domain.GetProductsFromApi
import br.gohan.products.domain.LocalCheckout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    savedStateHandle: SavedStateHandle,
    remoteData: GetProductsFromApi,
    val localCheckout: LocalCheckout,
    val appEvents: MutableSharedFlow<AppEvents>,
) : ViewModel() {
    private var _productsState = MutableStateFlow(savedStateHandle.get<SnapshotStateList<ProductsState>>("productsState") ?: initialList())
    var productsState = _productsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val productsSelected = localCheckout.getProductsSelected()
            val response = remoteData.getProductsList(productsSelected)
            if (response != null) {
                _productsState.value = response.toMutableStateList()
            } else {
                appEvents.emit(AppEvents.ApiError)
            }
        }
    }

    fun addProduct(product: ProductsState) {
        viewModelScope.launch(Dispatchers.IO) {
            _productsState.value = localCheckout.addProduct(_productsState.value, product)
        }
    }

    fun removeProduct(removeAll: Boolean, product: ProductsState) {
        viewModelScope.launch(Dispatchers.IO) {
            _productsState.value = localCheckout.removeProduct(removeAll, product, _productsState.value)
        }
    }

    fun showSnackbar(snackbarScope: CoroutineScope, snackBarHost: SnackbarHostState, name: String) {
        snackbarScope.launch {
            snackBarHost.showSnackbar("Produto $name adicionado ao carrinho")
        }
    }

    private fun initialList(): SnapshotStateList<ProductsState> {
        val product = ProductsState(name = "Loading",
            price = 1.0,
            description = "Loading",
            id = 1,
            image = "")
        val listOfProducts = mutableStateListOf<ProductsState>()
        repeat(10) {
            listOfProducts.add(product)
        }
        return listOfProducts
    }
}
