package com.example.mercado.remote.data.pos

import com.example.mercado.remote.data.common.Paging

data class POSSearchResponse(
    val paging: Paging,
    val results: List<POS>
)