package com.example.mercado.remote.domain

import com.example.mercado.BuildConfig
import com.example.mercado.remote.data.OAuthDTO
import com.example.mercado.remote.data.shops.StoreDTO
import com.example.mercado.remote.data.shops.CreateStoreRequest
import com.example.mercado.remote.data.shops.StoreResponseError
import com.google.gson.Gson
import retrofit2.HttpException

class MercadoAPI(private val service: MercadoService) {
    suspend fun refreshAccessToken(): OAuthDTO {
        return service.refreshAccessToken(
            clientID = BuildConfig.mercadoUserId,
            clientSecret = BuildConfig.mercadoApiKey,
            grantType = "refresh_token",
            refreshToken = BuildConfig.mercadoRefreshToken
        )
    }

    suspend fun getStore(externalID: String): StoreDTO? {
        try {
            val response = service.searchStoresByExternalID(BuildConfig.mercadoUserId, externalID)
            return response.results[0]
        } catch (error: HttpException) {
            if (error.code() == 404) {  // Shop not found
                return null
            }
            throw error
        }
    }

    suspend fun tryCreateStore(createStoreRequest: CreateStoreRequest): StoreDTO {
        try {
            return service.createNewStore(BuildConfig.mercadoUserId, createStoreRequest)
        } catch (error: HttpException) {
            if (error.code() == 400) {
                val errorResponseJSON = error.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorResponseJSON, StoreResponseError::class.java)

                if (errorResponse.error == "bad_request") {
                    return getStore(createStoreRequest.external_id)!!
                }

                if (errorResponse.error == "validation_error") {
                    throw Exception(errorResponse.causes[0].description)
                }
            }

            throw error
        }
    }

    suspend fun deleteStore(shopID: String): Boolean {
        return try {
            service.deleteStoreByID(BuildConfig.mercadoUserId, shopID)
            true
        } catch (error: HttpException) {
            false
        }
    }
}