package com.example.mercado.remote.data.pos

data class POS(
    val id: Long,
    val qr: QrData,
    val status: String?,
    val date_created: String,
    val date_last_updated: String,
    val uuid: String?,
    val user_id: Long,
    val name: String,
    val fixed_amount: Boolean,
    val category: Int?,
    val store_id: Long,
    val external_store_id: String?,
    val external_id: String
)

data class QrData(
    val image: String,
    val template_document: String,
    val template_image: String
)