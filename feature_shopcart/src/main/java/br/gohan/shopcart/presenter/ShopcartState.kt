package br.gohan.shopcart.presenter

data class ShopcartState(
    val name : String,
    val price : Double,
    val description : String,
    val id : Int,
    val image: String? = null
)
