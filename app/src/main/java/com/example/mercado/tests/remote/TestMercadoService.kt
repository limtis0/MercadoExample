package com.example.mercado.tests.remote

import com.example.mercado.remote.data.shops.Location
import com.example.mercado.remote.data.shops.ShopRequest
import com.example.mercado.remote.domain.MercadoAPI
import com.example.mercado.tests.DaggerTestComponent
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull

class TestMercadoService {
    @Inject
    lateinit var mercadoAPI: MercadoAPI

    private val shopData = ShopRequest(
        name = "TestShop",
        external_id = "TEST-SHP-001",
        location = Location(
            street_number = "3039",
            street_name = "Caseros",
            city_name = "Belgrano",
            state_name = "Capital Federal",
            latitude = -32.8897322,
            longitude = -68.8443275,
            reference = "3er Piso"
        )
    )

    @Before
    fun setup() {
        val appComponent = DaggerTestComponent.create()
        appComponent.inject(this)
    }

    @Test
    fun testInjection() {
        assertNotNull(mercadoAPI)
    }

    @Test
    fun testTryCreateShop() {
        runBlocking {
            // Act
            val shop = mercadoAPI.tryCreateShop(shopData)

            // Assert
            assertNotNull(shop)
            assertEquals(shopData.name, shop.name)
            assertEquals(shopData.external_id, shop.external_id)
        }
    }

    @Test
    fun testFindShopByExternalID() {
        runBlocking {
            // Arrange
            mercadoAPI.tryCreateShop(shopData)

            // Act
            val shop = mercadoAPI.getShop(shopData.external_id)

            // Assert
            assertNotNull(shop)
            shop!!

            assertEquals(shopData.external_id, shop.external_id)
            assertEquals(shopData.name, shop.name)
        }
    }

    @Test
    fun testFindShopByNonexistentID() {
        // Arrange
        val shopExternalID = "NONEXISTENT-SHOP000000"

        runBlocking {
            // Act
            val shop = mercadoAPI.getShop(shopExternalID)

            // Assert
            assertNull(shop)
        }
    }

    @Test
    fun testDeleteShop() {
        runBlocking {
            // Arrange
            val shop = mercadoAPI.tryCreateShop(shopData)

            // Act
            mercadoAPI.deleteShop(shop.id)

            val foundShop = mercadoAPI.getShop(shop.external_id)

            // Assert
            assertNull(foundShop)
        }
    }
}