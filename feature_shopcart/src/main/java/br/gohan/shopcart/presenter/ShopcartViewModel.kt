package br.gohan.shopcart.presenter

import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.core.AppEvents
import br.gohan.shopcart.data.ShopcartRepository
import br.gohan.shopcart.domain.GetShopcartList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopcartViewModel(
    getShopcartList: GetShopcartList,
    appEvents: MutableSharedFlow<AppEvents>,
    val repository: ShopcartRepository
) : ViewModel() {

    private var _shopcartState = MutableStateFlow(ShopcartState())
    var shopcartState = _shopcartState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val shopcartList = getShopcartList.invoke()
            _shopcartState.value = ShopcartState(shopcartList, calculateTotal(shopcartList))
        }
    }

    private fun calculateTotal(shopcartList: List<Shopcart>): Double {
        return shopcartList.sumOf { it.price * it.quantity }
    }

    fun handleEvents(
        event: ShopcartEvents,
        snackbarHostState: SnackbarHostState,
        snackbarScope: CoroutineScope
    ) {
        when (event) {
            is ShopcartEvents.RemoveAll -> {
                viewModelScope.launch(Dispatchers.IO) {
                    _shopcartState.emit(ShopcartState())
                    repository.removeAll()
                    showSnackbar(snackbarScope, snackbarHostState)
                }
            }
        }
    }

    private fun showSnackbar(snackbarScope: CoroutineScope, snackBarHost: SnackbarHostState) {
        snackbarScope.launch {
            snackBarHost.showSnackbar("Carrinho limpo")
        }
    }
}
