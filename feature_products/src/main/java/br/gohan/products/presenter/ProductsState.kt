package br.gohan.products.presenter

data class ProductsState(
    val name : String,
    val price : Double,
    val description : String,
    val id : Int,
    val quantity : Int = 0,
    val image: String? = null
)