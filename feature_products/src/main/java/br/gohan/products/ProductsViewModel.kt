package br.gohan.products

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductsViewModel : ViewModel() {
    private var _productsState = MutableStateFlow(ProductsState())
    var productsState = _productsState.asStateFlow()

}