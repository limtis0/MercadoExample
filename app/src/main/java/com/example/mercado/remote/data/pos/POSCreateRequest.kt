package com.example.mercado.remote.data.pos

data class POSCreateRequest(
    val name: String,
    val fixed_amount: Boolean,
    val store_id: Long,
    val external_store_id: String,
    val external_id: String,
    val category: Int? = null
)
