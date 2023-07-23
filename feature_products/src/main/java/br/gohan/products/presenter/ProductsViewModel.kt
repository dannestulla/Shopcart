package br.gohan.products.presenter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductsViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private var _productsState = MutableStateFlow(ProductsState())
    var productsState = _productsState.asStateFlow()

}