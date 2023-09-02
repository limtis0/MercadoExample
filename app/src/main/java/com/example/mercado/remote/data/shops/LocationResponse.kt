package com.example.mercado.remote.data.shops

data class LocationResponse(
    val address_line: String,
    val reference: String,
    val latitude: Double,
    val longitude: Double,
    val id: String,
    val type: String,
    val city: String
)