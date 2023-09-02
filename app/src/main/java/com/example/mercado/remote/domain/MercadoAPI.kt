package com.example.mercado.remote.domain

import com.example.mercado.BuildConfig
import com.example.mercado.remote.data.OAuthDTO
import com.example.mercado.remote.data.shops.ShopDTO
import com.example.mercado.remote.data.shops.ShopRequest

class MercadoAPI(private val service: MercadoService) {
    suspend fun refreshAccessToken(): OAuthDTO {
        return service.refreshAccessToken(
            clientId = BuildConfig.mercadoUserId,
            clientSecret = BuildConfig.mercadoApiKey,
            grantType = "refresh_token",
            refreshToken = BuildConfig.mercadoRefreshToken
        )
    }

    suspend fun getShopByExternalID(externalID: String): ShopDTO? {
        val response = service.searchShopsByExternalId(BuildConfig.mercadoUserId, externalID)

        if (response.paging.total == 0) {
            return null
        }

        return response.results[0]
    }

    suspend fun createShop(shopRequest: ShopRequest) {
        service.createNewShop(BuildConfig.mercadoUserId, shopRequest)
    }
}