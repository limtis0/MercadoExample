package com.example.mercado.remote.data.stores

import com.example.mercado.remote.data.common.Paging

data class StoreSearchResponse(
    val paging: Paging,
    val results: List<Store>
)