package com.example.mercado.remote.data.shops

data class ShopDTO(
    val id: String,
    val name: String,
    val date_creation: String,
    val location: LocationResponse,
    val external_id: String
)