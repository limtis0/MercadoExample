package com.example.mercado.remote.data.qr_tramma

import java.math.BigDecimal
import java.util.Date
import java.util.UUID

data class QRTrammaRequest(
    val title: String,
    val description: String,
    val notification_url: String,
    val total_amount: BigDecimal,
    val expiration_date: Date,
    val items: List<Item>,
    val external_reference: String = UUID.randomUUID().toString()
)
