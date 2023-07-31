package br.gohan.products.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.gohan.core.values.FontSize
import br.gohan.products.presenter.ProductsEvents
import br.gohan.products.presenter.ProductsState

@Composable
fun ProductCardToggled(products: List<ProductsState>, index: Int, event: (ProductsEvents) -> Unit) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {
            event.invoke(
                ProductsEvents.RemoveProduct(
                    product = products[index],
                    removeAll = true,
                )
            )
        }) {
            Icon(
                Icons.Rounded.Delete,
                contentDescription = "ClearButton",
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                event.invoke(ProductsEvents.RemoveProduct(products[index]))
            }) {
                Icon(
                    Icons.Default.Remove,
                    contentDescription = "Shopping Cart",
                )
            }
            Card {
                Text(
                    text = products[index].quantity.toString(),
                    textAlign = TextAlign.Center,
                    fontSize = FontSize.small,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(onClick = {
                event.invoke(ProductsEvents.AddProduct(products[index]))
            }) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = "Shopping Cart",
                )
            }

        }
    }
}