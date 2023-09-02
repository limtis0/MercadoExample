package com.example.mercado.tests.di

import com.example.mercado.tests.DaggerTestComponent
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import javax.inject.Inject

class TestDI {
    @Inject
    lateinit var retrofit: Retrofit

    @Before
    fun setup() {
        val appComponent = DaggerTestComponent.create()
        appComponent.inject(this)
    }

    @Test
    fun testInjection() {
        assertNotNull(retrofit)
    }
}