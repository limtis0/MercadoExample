package com.example.mercado.tests

import com.example.mercado.remote.domain.MercadoAPIModule
import com.example.mercado.retrofit.domain.RetrofitDIModule
import com.example.mercado.tests.di.TestDI
import com.example.mercado.tests.remote.TestMercadoService
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RetrofitDIModule::class,
        MercadoAPIModule::class
    ]
)
@Singleton
interface TestComponent {
    fun inject(testClass: TestDI)
    fun inject(testClass: TestMercadoService)
}
