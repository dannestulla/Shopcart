package br.gohan.shopcart.presenter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.gohan.core.utils.toCurrency
import br.gohan.core.values.FontSize
import org.koin.androidx.compose.getViewModel

@Composable
fun ShopcartScreen(snackbarHostState: SnackbarHostState, padding: PaddingValues) {
    val viewModel = getViewModel<ShopcartViewModel>()
    val shopcartState = viewModel.shopcartState.collectAsStateWithLifecycle()
    val snackbarScope = rememberCoroutineScope()
    ShopcartScreenStateless(shopcartState.value, padding) {
        viewModel.handleEvents(it, snackbarHostState, snackbarScope)
    }
}

@Composable
fun ShopcartScreenStateless(
    state: ShopcartState,
    padding: PaddingValues,
    events: (ShopcartEvents) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .padding(padding)
    ) {
        Text(
            text = "Total de itens: ${state.products.size}",
            maxLines = 1,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            items(
                state.products.size
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    val maxChars = 20
                    val dots = if (state.products[it].name.length > maxChars) "..." else ""
                    Text(
                        text = "${state.products[it].quantity}x ",
                        fontSize = FontSize.small,
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = state.products[it].name.take(maxChars) + dots,
                        fontSize = FontSize.medium,
                    )
                    Spacer(modifier = Modifier.weight(0.8f))
                    Text(
                        text = "R$ ${state.products[it].price}",
                        fontSize = FontSize.small,
                    )

                }
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()) {
            Text(
                text = "Total: ",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)
            Text(
                text = state.total.toCurrency(),
                fontSize = FontSize.mediumLarge,
                fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { events.invoke(ShopcartEvents.RemoveAll) }) {
            Text(
                text = "Limpar carrinho"
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ }) {
            Text(
                text = "Finalizar compra"
            )
        }
    }
}

@Preview
@Composable
fun ProductsScreenPreview() {
    ShopcartScreenStateless(
        padding = PaddingValues(),
        state =
        ShopcartState(
            total = 230.0,
            products =
            listOf(
                Shopcart(
                    name = "Casaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaco",
                    price = 100.0,
                    quantity = 1,
                ),
                Shopcart(
                    name = "Botas",
                    price = 20.0,
                    quantity = 2,
                ), Shopcart(
                    name = "Chapeu",
                    price = 10.0,
                    quantity = 1,
                ), Shopcart(
                    name = "Casaco",
                    price = 100.0,
                    quantity = 1,
                )


            )
        )
    ) {

    }
}