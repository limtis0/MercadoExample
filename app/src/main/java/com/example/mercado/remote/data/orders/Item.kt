package com.example.mercado.remote.data.orders

import java.math.BigDecimal

data class Item(
    val id: String,
    val category_id: String,
    val currency_id: String,
    val description: String,
    val picture_url: String?,
    val title: String,
    val quantity: Int,
    val unit_price: BigDecimal
)