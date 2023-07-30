package br.gohan.products.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.gohan.core.AppEvents
import br.gohan.feature_products.R
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductsScreen(appEvents: MutableSharedFlow<AppEvents>, snackbarHostState: SnackbarHostState) {
    val viewModel = getViewModel<ProductsViewModel>()
    val productsState = viewModel.productsState.collectAsStateWithLifecycle()
    val snackbarScope = rememberCoroutineScope()
    ProductsScreenStateless(productsState.value) { event->
        handleEvents(event, viewModel, appEvents, snackbarScope, snackbarHostState)
    }
}

@Composable
fun ProductsScreenStateless(products: List<ProductsState>, event: (ProductsEvents) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn {
            items(
                products.size
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { }
                            .padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .width(75.dp)
                                .height(75.dp),
                            model = products[it].image,
                            placeholder = painterResource(R.drawable.orange_svgrepo_com),
                            contentDescription = null,
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = products[it].name,
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = products[it].description,
                                maxLines = 2,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = products[it].price.toString(),
                                textAlign = TextAlign.Center,
                                maxLines = 2,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Button(
                                    enabled = products[it].enabled,
                                    onClick = {
                                        event(ProductsEvents.SaveProduct(products[it]))

                                }) {
                                    Text(text = "Add")
                                    Icon(
                                        Icons.Rounded.Add,
                                        contentDescription = "Shopping Cart",
                                    )
                                }

                            }
                        }

                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun ProductsScreenPreview() {
    ProductsScreenStateless(
        products =
            listOf(
                ProductsState(
                    name = "Laranja",
                    price = 2.0,
                    description = "Fruta",
                    id = 12,
                    image = ""
                )
            ),
        event = {}
        )
}