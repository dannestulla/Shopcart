package br.gohan.products.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.core.AppEvents
import br.gohan.products.domain.GetProductsList
import br.gohan.products.domain.toProductsState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductsViewModel(
    savedStateHandle: SavedStateHandle,
    getProductsList: GetProductsList,
    events: MutableSharedFlow<AppEvents>
) : ViewModel() {
    private var _productsState = MutableStateFlow(listOf<ProductsState>())
    var productsState = _productsState.asStateFlow()

    init {
        viewModelScope.launch {
            val response = getProductsList.invoke()
            if (response.isSuccessful) {
                _productsState.value = response.body()?.toProductsState() ?: emptyList()
            } else {
                events.emit(AppEvents.ApiError)
            }
        }
    }
}