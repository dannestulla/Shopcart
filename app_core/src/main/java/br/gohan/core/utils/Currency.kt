package br.gohan.core.utils

import java.text.NumberFormat
import java.util.Locale

fun Double.toCurrency() = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)