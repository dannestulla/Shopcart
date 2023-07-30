package br.gohan.products.presenter

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.core.AppEvents
import br.gohan.products.domain.GetProductsFromApi
import br.gohan.products.domain.ManageCheckout
import br.gohan.products.domain.toProductsState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    savedStateHandle: SavedStateHandle,
    getProductsFromApi: GetProductsFromApi,
    val manageCheckout: ManageCheckout,
    val appEvents: MutableSharedFlow<AppEvents>,
) : ViewModel() {
    private var _productsState = MutableStateFlow(listOf<ProductsState>())
    var productsState = _productsState.asStateFlow()

    init {
        viewModelScope.launch {
            val response = getProductsFromApi.invoke()
            if (response.isSuccessful) {
                _productsState.value = response.body()?.toProductsState() ?: emptyList()
            } else {
                appEvents.emit(AppEvents.ApiError)
            }
        }
    }

    fun saveProduct(product: ProductsState) {
        viewModelScope.launch(Dispatchers.IO) {
            disableButton(product)
            manageCheckout.saveProduct(product)
        }
    }

    private fun disableButton(product: ProductsState) {
        _productsState.value = _productsState.value.map {
            if (it.id == product.id) {
                it.copy(enabled = false)
            } else {
                it
            }
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            manageCheckout.deleteAll()
        }
    }

    fun showSnackbar(snackbarScope: CoroutineScope, snackBarHost: SnackbarHostState) {
        snackbarScope.launch {
            snackBarHost.showSnackbar("Produto adicionado ao carrinho")
        }
    }
}
