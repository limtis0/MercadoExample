package com.example.mercado.tests.remote

import com.example.mercado.remote.data.shops.Location
import com.example.mercado.remote.data.shops.CreateStoreRequest
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

    private val storeData = CreateStoreRequest(
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
            val store = mercadoAPI.tryCreateStore(storeData)

            // Assert
            assertNotNull(store)
            assertEquals(storeData.name, store.name)
            assertEquals(storeData.external_id, store.external_id)
        }
    }

    @Test
    fun testFindShopByExternalID() {
        runBlocking {
            // Arrange
            mercadoAPI.tryCreateStore(storeData)

            // Act
            val store = mercadoAPI.getStore(storeData.external_id)

            // Assert
            assertNotNull(store)
            store!!

            assertEquals(storeData.external_id, store.external_id)
            assertEquals(storeData.name, store.name)
        }
    }

    @Test
    fun testFindShopByNonexistentID() {
        // Arrange
        val shopExternalID = "NONEXISTENT-SHOP000000"

        runBlocking {
            // Act
            val store = mercadoAPI.getStore(shopExternalID)

            // Assert
            assertNull(store)
        }
    }

    @Test
    fun testDeleteShop() {
        runBlocking {
            // Arrange
            val store = mercadoAPI.tryCreateStore(storeData)

            // Act
            mercadoAPI.deleteStore(store.id)

            val foundStore = mercadoAPI.getStore(store.external_id)

            // Assert
            assertNull(foundStore)
        }
    }
}