package com.example.mercado.remote.data.orders

data class OrderSearch(
    val elements: List<Order>,
    val next_offset: Int,
    val total: Int
)