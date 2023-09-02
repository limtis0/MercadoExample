package com.example.mercado.remote.domain

import com.example.mercado.remote.data.OAuthDTO
import com.example.mercado.remote.data.stores.CreateStoreRequest
import com.example.mercado.remote.data.stores.StoreDTO
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
interface MercadoService {
    @Headers("Content-Type: application/json")
    @FormUrlEncoded
    @POST("oauth/token")
    suspend fun refreshAccessToken(
        @Field("client_id") clientID: String,
        @Field("client_secret") clientSecret: String,
        @Field("grant_type") grantType: String,  // Should equal "refresh_token"
        @Field("refresh_token") refreshToken: String
    ): OAuthDTO

    @Headers("Content-Type: application/json")
    @POST("/users/{user_id}/stores")
    suspend fun createNewStore(
        @Path("user_id") userID: String,
        @Body createStoreRequest: CreateStoreRequest
    ): StoreDTO

    @Headers("Content-Type: application/json")
    @GET("/users/{user_id}/stores/search")
    suspend fun searchStoresByExternalID(
        @Path("user_id") userID: String,
        @Query("external_id") externalId: String
    ): StoreSearchResponse

    @Headers("Content-Type: application/json")
    @DELETE("/users/{user_id}/stores/{id}")
    suspend fun deleteStoreByID(
        @Path("user_id") userID: String,
        @Path("id") shopID: String
    ): StoreDeleteResponse
}