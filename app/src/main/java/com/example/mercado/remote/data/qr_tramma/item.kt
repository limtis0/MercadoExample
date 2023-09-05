package com.example.mercado.remote.data.qr_tramma

import java.math.BigDecimal

data class Item(
    val sku_number: String,
    val category: String,
    val title: String,
    val description: String,
    val unit_price: BigDecimal,
    val quantity: Int,
    val unit_measure: String,
    val total_amount: BigDecimal
)