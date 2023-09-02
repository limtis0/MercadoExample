package com.example.mercado.remote.domain

import com.example.mercado.remote.data.OAuthDTO
import com.example.mercado.remote.data.shops.ShopRequest
import com.example.mercado.remote.data.shops.ShopDTO
import com.example.mercado.remote.data.shops.StoreSearchResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// MercadoPago API Description
interface MercadoService {
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun refreshAccessToken(
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,  // Should equal "refresh_token"
        @Field("refresh_token") refreshToken: String
    ): OAuthDTO

    @Headers("Content-Type: application/json")
    @POST("/users/{user_id}/stores")
    suspend fun createNewShop(
        @Path("user_id") userId: String,
        @Body shopRequest: ShopRequest
    ): ShopDTO

    @Headers("Content-Type: application/json")
    @GET("/users/{user_id}/stores/search")
    suspend fun searchShopsByExternalId(
        @Path("user_id") userId: String,
        @Query("external_id") externalId: String
    ): StoreSearchResponse
}