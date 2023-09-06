package com.example.mercado.remote.domain

import com.example.mercado.BuildConfig
import com.example.mercado.remote.data.OAuth
import com.example.mercado.remote.data.orders.Order
import com.example.mercado.remote.data.pos.POS
import com.example.mercado.remote.data.pos.POSCreateRequest
import com.example.mercado.remote.data.qr_tramma.QRTramma
import com.example.mercado.remote.data.qr_tramma.QRTrammaRequest
import com.example.mercado.remote.data.stores.Store
import com.example.mercado.remote.data.stores.StoreCreateRequest
import com.example.mercado.remote.data.stores.StoreResponseError
import com.google.gson.Gson
import retrofit2.HttpException

class MercadoAPI(private val service: IMercadoService) {
    suspend fun refreshAccessToken(): OAuth {
        return service.refreshAccessToken(
            clientID = BuildConfig.mercadoUserId,
            clientSecret = BuildConfig.mercadoApiKey,
            grantType = "refresh_token",
            refreshToken = BuildConfig.mercadoRefreshToken
        )
    }

    suspend fun getStore(externalID: String): Store? {
        try {
            val response = service.searchStores(BuildConfig.mercadoUserId, externalID)
            return response.results[0]
        } catch (error: HttpException) {
            if (error.code() == 404) {  // Shop not found
                return null
            }
            throw error
        }
    }

    suspend fun tryCreateStore(storeCreateRequest: StoreCreateRequest): Store {
        try {
            return service.createNewStore(BuildConfig.mercadoUserId, storeCreateRequest)
        } catch (error: HttpException) {
            if (error.code() == 400) {
                val errorResponseJSON = error.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorResponseJSON, StoreResponseError::class.java)

                if (errorResponse.error == "bad_request") {
                    return getStore(storeCreateRequest.external_id)!!
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

    suspend fun getPOS(externalID: String): POS? {
        val response = service.searchPOS(externalID)

        if (response.paging.total == 0) {
            return null
        }

        return response.results[0]
    }

    suspend fun tryCreatePOS(posCreateRequest: POSCreateRequest): POS {
        try {
            return service.createPOS(posCreateRequest)
        } catch (error: HttpException) {
            if (error.code() == 409) {
                return getPOS(externalID = posCreateRequest.external_id)!!
            }

            throw error
        }
    }

    suspend fun createQRTramma(
        externalPOSID: String,
        qrTrammaRequest: QRTrammaRequest
    ): QRTramma {
        return service.createQRTramma(
            BuildConfig.mercadoUserId,
            externalPOSID,
            qrTrammaRequest
        )
    }

    suspend fun getOrder(externalReference: String): Order? {
        val orders = service.searchMerchantOrders(externalReference)

        if (orders.total >= 1) {
            return orders.elements[0]
        }

        return null
    }
}