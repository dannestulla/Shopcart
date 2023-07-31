package br.gohan.shopcart.presenter

import androidx.compose.runtime.Composable

sealed class ShopcartEvents {
    object RemoveAll : ShopcartEvents()
}
