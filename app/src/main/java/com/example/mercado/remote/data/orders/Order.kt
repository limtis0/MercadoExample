package com.example.mercado.remote.data.orders

import java.math.BigDecimal

data class Order(
    val id: Long,
    val status: String,
    val external_reference: String,
    // val preference_id: String,
    // val payments: List<Payment>,
    // val shipments: List<Any>,
    // val payouts: List<Any>,
    // val collector: Collector,
    // val marketplace: String,
    // val notification_url: String,
    val date_created: String,
    val last_updated: String,
    // val sponsor_id: Any?,
    // val shipping_cost: Int,
    val total_amount: BigDecimal,
    // val site_id: String,
    val paid_amount: BigDecimal,
    val refunded_amount: BigDecimal,
    // val payer: Payer,
    val items: List<Item>,
    val cancelled: Boolean,
    // val additional_info: String,
    // val application_id: Any?,
    // val is_test: Boolean,
    val order_status: String
)