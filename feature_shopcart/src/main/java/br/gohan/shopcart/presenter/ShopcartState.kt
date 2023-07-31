package br.gohan.shopcart.presenter

data class ShopcartState(
    val products : List<Shopcart> = emptyList(),
    val total : Double = 0.0
)

data class Shopcart(
    val name : String,
    val price : Double,
    val quantity : Int = 0,
)