package br.gohan.products.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CategoryRow() {
    val list = listOf(
        "A", "B", "C", "D"
    ) + ((0..100).map { it.toString() })
    LazyRow(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        items(list.size) { item ->
            Card(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
                    .padding(5.dp)
            ) {
                Text(
                    text = item.toString()
                )
            }
        }
    }
}
