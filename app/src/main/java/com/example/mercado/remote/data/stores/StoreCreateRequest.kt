package com.example.mercado.remote.data.stores

data class StoreCreateRequest(
    val name: String,
    val external_id: String,
    val location: Location
)

data class Location(
    val street_number: String,
    val street_name: String,
    val city_name: String,
    val state_name: String,
    val latitude: Double,
    val longitude: Double,
    val reference: String
)