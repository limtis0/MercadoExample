package com.example.mercado.remote.data.shops

data class StoreSearchResponse(
    val paging: Paging,
    val results: List<ShopDTO>
)

data class Paging(
    val total: Int,
    val offset: Int,
    val limit: Int
)