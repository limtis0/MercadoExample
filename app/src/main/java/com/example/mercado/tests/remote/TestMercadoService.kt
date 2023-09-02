package com.example.mercado.tests.remote

import com.example.mercado.remote.domain.MercadoAPI
import com.example.mercado.tests.DaggerTestComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import javax.inject.Inject
import org.junit.Assert.assertNotNull


class TestMercadoService {
    @Inject
    lateinit var mercadoAPI: MercadoAPI

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
    fun testShopExists() {
        // Arrange
        // This shop is person-made and test may fail in future
        val shopName = "TestShop"
        val shopID = "57285808"
        val shopExternalID = "TEST-SHP-001"

        runBlocking {
            // Act
            val shop = mercadoAPI.getShopByExternalID(shopExternalID)

            // Assert
            assertNotNull(shop)
            shop!!

            assertEquals(shopExternalID, shop.external_id)
            assertEquals(shopName, shop.name)
            assertEquals(shopID, shop.id)
        }
    }
}