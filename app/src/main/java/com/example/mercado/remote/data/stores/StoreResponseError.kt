package com.example.mercado.remote.data.stores

data class StoreResponseError(
    val error: String,
    val message: String,
    val status: Int,
    val causes: List<Cause>
)

data class Cause(
    val code: Int,
    val description: String
)