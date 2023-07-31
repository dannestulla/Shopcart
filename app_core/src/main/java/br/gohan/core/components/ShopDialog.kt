package br.gohan.core.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import br.gohan.core.values.FontSize


@Composable
fun ShopDialog(
    callback: () -> Unit
) {
    AlertDialog(onDismissRequest = { },
        text = {
            Text(
                text = "Deseja limpar o carrinho?",
                fontSize = FontSize.medium
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    callback.invoke()
                }) {
                Text(
                    text = "Sim"
                )
            }
        }, dismissButton = {
            Button(
                onClick = {
                }) {
                Text(text = "Não")
            }
        })
}
