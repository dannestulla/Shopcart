package br.gohan.products.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.gohan.core.utils.toCurrency
import br.gohan.core.values.FontSize
import br.gohan.products.presenter.ProductsEvents
import br.gohan.products.presenter.ProductsState

@Composable
fun ProductCardNotToggled(
    products: List<ProductsState>,
    index: Int,
    event: (ProductsEvents) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = {
                event(ProductsEvents.AddProduct(products[index]))

            }) {
            Icon(
                Icons.Rounded.Add,
                contentDescription = "Shopping Cart",
            )
        }
        Text(
            text = products[index].price.toCurrency(),
            textAlign = TextAlign.Center,
            maxLines = 1,
            fontSize = FontSize.small,
        )
    }
}
