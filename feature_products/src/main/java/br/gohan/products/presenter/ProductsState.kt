package br.gohan.products.presenter

data class ProductsState(
    val name : String,
    val price : Double,
    val description : String,
    val id : Int,
    val image: String? = null
)
