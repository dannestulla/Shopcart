package br.gohan.shopcart

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.core.AppRoutes

@Composable
fun BottomNavigationBar(selected: String, callback: (AppRoutes) -> Unit, ) {
    return BottomAppBar {
        NavigationBarItem(
            selected = selected == AppRoutes.FEATURE_PRODUCTS.route,
            onClick = {
                callback.invoke(AppRoutes.FEATURE_PRODUCTS) },
            label = {
                Text(
                    text = "Produtos",
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.List,
                    contentDescription =  "Products Icon",
                )
            }
        )
        NavigationBarItem(
            selected = selected == AppRoutes.FEATURE_SHOPCART.route,
            onClick = { callback.invoke(AppRoutes.FEATURE_SHOPCART) },
            label = {
                Text(
                    text = "Carrinho",
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "ShoppingCard Icon",
                )
            }
        )
    }
}