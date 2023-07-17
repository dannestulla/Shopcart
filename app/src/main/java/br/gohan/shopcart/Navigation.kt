package br.gohan.shopcart

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.gohan.core.AppRoutes
import br.gohan.feature_carrinho.ShopcartScreen
import br.gohan.feature_example.ProductsScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.FEATURE_PRODUCTS.route
    ) {
        composable(route = AppRoutes.FEATURE_PRODUCTS.route) {
            ProductsScreen()
        }
        composable(route = AppRoutes.FEATURE_SHOPCART.route) {
            ShopcartScreen()
        }
    }
}
