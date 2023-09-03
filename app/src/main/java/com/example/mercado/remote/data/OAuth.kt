package com.example.mercado.remote.data

import com.google.gson.annotations.SerializedName

data class OAuth(
    @SerializedName("access_token") val accessToken: String,
    @SerializedName("token_type") val tokenType: String,
    @SerializedName("expires_in") val expiresIn: Long,
    @SerializedName("scope") val scope: String,
    @SerializedName("user_id") val userId: String,
    @SerializedName("refresh_token") val refreshToken: String,
    @SerializedName("public_key") val publicKey: String
)