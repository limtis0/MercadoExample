package com.example.mercado.remote.data.stores

data class Store(
    val id: String,
    val name: String,
    val date_creation: String,
    val location: LocationResponse,
    val external_id: String
)