package br.gohan.products.presenter

data class ProductsState(
    val productsList : List<Products>? = null
)

data class Products(
    val name : String,
    val price : Double,
    val description : String,
    val id : Int,
    val quantity : Int,
    val image: String
)