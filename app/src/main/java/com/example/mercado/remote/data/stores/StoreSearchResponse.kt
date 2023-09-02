package com.example.mercado.remote.data.stores

data class StoreSearchResponse(
    val paging: Paging,
    val results: List<StoreDTO>
)

data class Paging(
    val total: Int,
    val offset: Int,
    val limit: Int
)