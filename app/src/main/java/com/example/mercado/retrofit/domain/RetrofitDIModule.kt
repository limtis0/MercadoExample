package com.example.mercado.retrofit.domain

import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val BASE_URL = "https://api.mercadopago.com"

@Module
class RetrofitDIModule {
    // This provides Retrofit instance as a singleton via DI
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}