package br.gohan.products.presenter

import android.content.SharedPreferences
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.core.AppEvents
import br.gohan.products.domain.GetProductsFromApi
import br.gohan.products.domain.LocalCheckout
import br.gohan.products.domain.toProductState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    remoteData: GetProductsFromApi,
    val localCheckout: LocalCheckout,
    val appEvents: MutableSharedFlow<AppEvents>,
) : ViewModel() {
    private var _productsState = MutableStateFlow(mutableStateListOf<ProductsState>())
    var productsState = _productsState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val productsSelected = localCheckout.getProductsSelected()
            val response = remoteData.getProductsList()
            if (response.isSuccessful) {
                val body = response.body()?.toProductState()
                body?.map { product ->
                    product.isLoading = false
                    productsSelected.forEach { productSelected ->
                        if (product.id == productSelected.id) {
                            product.quantity = productSelected.quantity
                        }
                    }
                }
                _productsState.value = body?.toMutableStateList() ?: mutableStateListOf()
            } else {
                appEvents.emit(AppEvents.ApiError)
            }
        }
    }

    fun addProduct(product: ProductsState) {
        viewModelScope.launch(Dispatchers.IO) {
            _productsState.value = _productsState.value.map {
                if (it.id == product.id) {
                    it.copy(quantity = ++it.quantity)
                } else {
                    it
                }
            }.toMutableStateList()
            localCheckout.addProduct(product.copy(quantity = ++product.quantity))
        }
    }

    fun removeProduct(removeAll: Boolean, product: ProductsState) {
        viewModelScope.launch(Dispatchers.IO) {
            _productsState.value = _productsState.value.map {
                if (it.id == product.id) {
                    if (removeAll) {
                        it.copy(quantity = 0)
                    } else {
                        it.copy(quantity = --it.quantity)
                    }
                } else {
                    it
                }
            }.toMutableStateList()
            localCheckout.removeProduct(removeAll, product)
        }
    }

    fun showSnackbar(snackbarScope: CoroutineScope, snackBarHost: SnackbarHostState, name: String) {
        snackbarScope.launch {
            snackBarHost.showSnackbar("Produto $name adicionado ao carrinho")
        }
    }
}
