package com.example.mercado.retrofit.domain

import okhttp3.Interceptor
import okhttp3.Response

// Adds authorization to each request
class AuthInterceptor(private val authToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val modifiedRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $authToken")
            .build()
        return chain.proceed(modifiedRequest)
    }
}