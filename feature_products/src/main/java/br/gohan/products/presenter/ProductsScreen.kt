package br.gohan.products.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.gohan.core.AppEvents
import br.gohan.core.components.ShimmerCard
import br.gohan.core.values.FeatureFlags
import br.gohan.core.values.FontSize
import br.gohan.feature_products.R
import br.gohan.products.presenter.components.CategoryRow
import br.gohan.products.presenter.components.ProductCardNotToggled
import br.gohan.products.presenter.components.ProductCardToggled
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.MutableSharedFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProductsScreen(
    appEvents: MutableSharedFlow<AppEvents>,
    snackbarHostState: SnackbarHostState,
    viewModel: ProductsViewModel = koinViewModel(),
    padding: PaddingValues,
) {
    val productsState = viewModel.productsState.collectAsStateWithLifecycle()
    val snackbarScope = rememberCoroutineScope()
    val categoriasFlag = FeatureFlags.getFeatureFlags()["categorias"]
    ProductsScreenStateless(productsState.value, padding, categoriasFlag) { event ->
        handleEvents(event, viewModel, appEvents, snackbarScope, snackbarHostState)
    }
}

@Composable
fun ProductsScreenStateless(
    products: SnapshotStateList<ProductsState>,
    padding: PaddingValues,
    categoriasFlag: Boolean? = true,
    event: (ProductsEvents) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(padding)
    ) {
        if (categoriasFlag == true) {
            CategoryRow()
        }
        LazyColumn {
            items(
                products.size
            ) { index ->
                ShimmerCard(isLoading = products[index].isLoading, contentAfterLoading = {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .background(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .width(75.dp)
                                    .height(75.dp),
                                model = products[index].image,
                                placeholder = painterResource(R.drawable.orange_svgrepo_com),
                                contentDescription = null,
                            )
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Start),
                                    text = products[index].name,
                                    maxLines = 1,
                                    fontSize = FontSize.medium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    modifier = Modifier
                                        .align(Alignment.Start)
                                        .padding(end = 10.dp),
                                    text = products[index].description,
                                    fontStyle = FontStyle.Italic,
                                    lineHeight = 15.sp,
                                    maxLines = 2,
                                    fontSize = FontSize.extraSmall,
                                )
                            }
                            if (products[index].quantity > 0) {
                                ProductCardToggled(products, index, event)
                            } else {
                                ProductCardNotToggled(products, index, event)
                            }
                        }
                    }
                })
            }
        }
    }
}

@Preview
@Composable
fun ProductsScreenPreview() {
    val mockList = remember {
        mutableStateListOf(
            ProductsState(
                name = "Mens Cotton jacket pink brown",
                price = 2.0,
                description = "Great outwear jackets for Spring/ Autumn/ Winter, suitable for many occasions, " +
                        "such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoor adventure. " +
                        "Good gift choice for you or your family member. ",
                id = 12,
                image = ""
            )
        )
    }
    ProductsScreenStateless(
        products = mockList,
        padding = PaddingValues(),
        event = {}
    )
}