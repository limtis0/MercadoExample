package com.example.mercado.remote

import com.example.mercado.remote.data.OAuthDTO
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface  MercadoAPI {
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun refreshAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,  // Should equal "refresh_token"
        @Field("refresh_token") refreshToken: String
    ): OAuthDTO
}