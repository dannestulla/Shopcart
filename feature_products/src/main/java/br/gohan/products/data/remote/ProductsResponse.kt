package br.gohan.products.data.remote

data class ProductsData(
    val title: String,
    val id: Int,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: RatingData
)

data class RatingData(
    val rate: Double,
    val count: Int
)
