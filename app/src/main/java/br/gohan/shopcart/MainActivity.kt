package br.gohan.shopcart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.gohan.core.AppRoutes
import br.gohan.shopcart.ui.theme.ShopCartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShopCartTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                val selectedRoute = backStackEntry.value?.destination?.route ?: AppRoutes.FEATURE_PRODUCTS.route
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(selectedRoute) {
                            navController.navigate(it.route)
                        }
                    }
                ) { paddingValues ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .fillMaxWidth(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Navigation(navController)
                    }
                }
            }
        }
    }
}