package com.example.mercado.remote.domain

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MercadoAPIModule {
    @Provides
    @Singleton
    fun provideMercadoAPI(retrofit: Retrofit): MercadoAPI {
        val service = retrofit.create(IMercadoService::class.java)
        return MercadoAPI(service)
    }
}