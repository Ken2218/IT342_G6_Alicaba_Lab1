package com.example.myapplication

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    // Explicitly declaring the type 'ApiService' here is necessary to fix the inference error
    val instance: ApiService by lazy {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Pass ApiService::class.java explicitly to resolve the T variable error
        retrofit.create(ApiService::class.java)
    }
}