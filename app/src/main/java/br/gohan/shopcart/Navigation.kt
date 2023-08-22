package br.gohan.shopcart

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.gohan.core.AppEvents
import br.gohan.core.AppRoutes
import br.gohan.shopcart.presenter.ShopcartScreen
import br.gohan.products.presenter.ProductsScreen
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun Navigation(
    navController: NavHostController,
    appEvents: MutableSharedFlow<AppEvents>,
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.FEATURE_PRODUCTS.route
    ) {
        composable(route = AppRoutes.FEATURE_PRODUCTS.route) {
            ProductsScreen(appEvents, snackbarHostState, padding = padding)
        }
        composable(route = AppRoutes.FEATURE_SHOPCART.route) {
            ShopcartScreen(snackbarHostState, padding)
        }
    }
}
