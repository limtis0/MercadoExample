package com.example.mercado.ui.screens.domain

import com.example.mercado.MainActivity
import com.example.mercado.remote.data.pos.POS
import com.example.mercado.remote.data.pos.POSCreateRequest
import com.example.mercado.remote.data.stores.Location
import com.example.mercado.remote.data.stores.Store
import com.example.mercado.remote.data.stores.StoreCreateRequest
import com.example.mercado.remote.domain.MercadoAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainScreenDomain {
    @Inject
    lateinit var mercadoAPI: MercadoAPI

    private var store: Store? = null
    private var pos: POS? = null

    init {
        MainActivity.appComponent.inject(this)
    }

    suspend fun getTestPOS(): POS {
        return withContext(Dispatchers.IO) {
            if (pos != null) {
                pos!!
            }

            if (store == null) {
                store = createTestStore()
            }

            pos = createTestPOS(store!!)
            pos!!
        }
    }

    private suspend fun createTestStore(): Store {
        return mercadoAPI.tryCreateStore(
            StoreCreateRequest(
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
        )
    }

    private suspend fun createTestPOS(store: Store): POS {
        return mercadoAPI.tryCreatePOS(
            POSCreateRequest(
                name = "TestPOS",
                fixed_amount = false,
                store_id = store.id.toLong(),
                external_store_id = "TEST-SHP-001",
                external_id = "TEST001"
            )
        )
    }
}