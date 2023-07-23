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
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.gohan.core.utils.debugPlaceholder
import br.gohan.feature_products.R
import coil.compose.AsyncImage
import org.koin.androidx.compose.getViewModel

@Composable
fun ProductsScreen() {
    val viewModel = getViewModel<ProductsViewModel>()
    val productsState = viewModel.productsState.collectAsStateWithLifecycle()
    ProductsScreenStateless(productsState.value)
}

@Composable
fun ProductsScreenStateless(productsState: ProductsState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val products = productsState.productsList ?: emptyList()
        LazyColumn {
            items(
                productsState.productsList?.size ?: 0
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
                            placeholder = debugPlaceholder(R.drawable.orange_svgrepo_com),
                            contentDescription = null,
                        )
                        Column(modifier = Modifier
                            .weight(1f)
                            .align(Alignment.CenterVertically)) {
                            Text(
                                text = products[it].name,
                                textAlign = TextAlign.Center,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = products[it].description,
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Normal
                            )
                        }
                        Column(
                            horizontalAlignment = Alignment.End,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Icon(
                                Icons.Rounded.Delete,
                                contentDescription = stringResource(R.string.clear_button),
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Remove,
                                    contentDescription = "Shopping Cart",
                                )
                                Card {
                                    Text(
                                        text = products[it].quantity.toString(),
                                        textAlign = TextAlign.Center,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
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

@Preview
@Composable
fun ProductsScreenPreview() {
    ProductsScreenStateless(
        productsState = ProductsState(
            listOf(
                Products(
                    name = "Laranja",
                    price = 2.0,
                    description = "Fruta",
                    id = 12,
                    quantity = 2,
                    image = "https://media.gettyimages.com/id/185284489/pt/foto/de-laranja.jpg?s=2048x2048&w=gi&k=20&c=aD2VbAeEFeX_1if_9bYu57TrH084SPBhHGWcSOSHQrM="
                )
            )
        )
    )
}