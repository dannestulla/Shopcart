package br.gohan.shopcart.presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.gohan.core.AppEvents
import br.gohan.core.database.products.ProductsDao
import br.gohan.shopcart.domain.GetShopcartList
import br.gohan.shopcart.domain.toShopcartState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShopcartViewModel(
    getShopcartList: GetShopcartList,
    events: MutableSharedFlow<AppEvents>
) : ViewModel() {
    private var _shopcartState = MutableStateFlow(createShopcartList())
    var shopcartState = _shopcartState.asStateFlow()

    init {
        viewModelScope.launch {
            ProductsDao().getAll()
            val response = getShopcartList.invoke()
            if (response.isSuccessful) {
                _shopcartState.value = response.body()?.toShopcartState() ?: emptyList()
            } else {
                events.emit(AppEvents.ApiError)
            }
        }
    }

    fun createShopcartList(): List<ShopcartState> {
        return listOf(
            ShopcartState(
                "Fruta",
                38.90,
                "Descrição",
                1,
            ),
            ShopcartState(
                "Fruta",
                38.90,
                "Descrição",
                2,
            ),
            ShopcartState(
                "Fruta",
                38.90,
                "Descrição",
                3,
            )
        )
    }
}