package com.example.mercado.remote.domain

import com.example.mercado.remote.data.OAuth
import com.example.mercado.remote.data.pos.POS
import com.example.mercado.remote.data.pos.POSCreateRequest
import com.example.mercado.remote.data.pos.POSSearchResponse
import com.example.mercado.remote.data.stores.StoreCreateRequest
import com.example.mercado.remote.data.stores.Store
import com.example.mercado.remote.data.stores.StoreDeleteResponse
import com.example.mercado.remote.data.stores.StoreSearchResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

// MercadoPago API Description
interface IMercadoService {
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun refreshAccessToken(
        @Field("client_id") clientID: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,  // Should equal "refresh_token"
        @Field("refresh_token") refreshToken: String
    ): OAuth

    @Headers("Content-Type: application/json")
    @POST("/users/{user_id}/stores")
    suspend fun createNewStore(
        @Path("user_id") userID: String,
        @Body storeCreateRequest: StoreCreateRequest
    ): Store

    @Headers("Content-Type: application/json")
    @GET("/users/{user_id}/stores/search")
    suspend fun searchStores(
        @Path("user_id") userID: String,
        @Query("external_id") externalId: String
    ): StoreSearchResponse

    @Headers("Content-Type: application/json")
    @DELETE("/users/{user_id}/stores/{id}")
    suspend fun deleteStoreByID(
        @Path("user_id") userID: String,
        @Path("id") shopID: String
    ): StoreDeleteResponse

    @Headers("Content-Type: application/json")
    @GET("/pos")
    suspend fun searchPOS(
        @Query("external_id") externalId: String,
        // Other optional queries may be added the same way
        @Query("external_store_id") externalStoreId: String? = null
    ): POSSearchResponse

    @Headers("Content-Type: application/json")
    @POST("/pos")
    suspend fun createPOS(
        @Body posCreateRequest: POSCreateRequest
    ): POS
}