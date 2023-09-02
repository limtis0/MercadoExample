package com.example.mercado.remote.domain

import android.util.Log
import com.example.mercado.BuildConfig
import com.example.mercado.remote.data.OAuthDTO
import com.example.mercado.remote.data.shops.ShopDTO
import com.example.mercado.remote.data.shops.ShopRequest
import com.example.mercado.remote.data.shops.ShopResponseError
import com.google.gson.Gson
import retrofit2.HttpException

class MercadoAPI(private val service: MercadoService) {
    suspend fun refreshAccessToken(): OAuthDTO {
        return service.refreshAccessToken(
            clientId = BuildConfig.mercadoUserId,
            clientSecret = BuildConfig.mercadoApiKey,
            grantType = "refresh_token",
            refreshToken = BuildConfig.mercadoRefreshToken
        )
    }

    suspend fun getShop(externalID: String): ShopDTO? {
        try {
            val response = service.searchShopsByExternalID(BuildConfig.mercadoUserId, externalID)
            return response.results[0]
        } catch (error: HttpException) {
            if (error.code() == 404) {  // Shop not found
                return null
            }
            throw error
        }
    }

    suspend fun tryCreateShop(shopRequest: ShopRequest): ShopDTO {
        try {
            return service.createNewShop(BuildConfig.mercadoUserId, shopRequest)
        } catch (error: HttpException) {
            if (error.code() == 400) {
                val errorResponseJSON = error.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorResponseJSON, ShopResponseError::class.java)

                if (errorResponse.error == "bad_request") {
                    return getShop(shopRequest.external_id)!!
                }

                if (errorResponse.error == "validation_error") {
                    throw Exception(errorResponse.causes[0].description)
                }
            }

            throw error
        }
    }

    suspend fun deleteShop(shopID: String): Boolean {
        return try {
            service.deleteShopByID(BuildConfig.mercadoUserId, shopID)
            true
        } catch (error: HttpException) {
            false
        }
    }
}