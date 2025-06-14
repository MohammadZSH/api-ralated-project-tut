package com.test.tut_api_project.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitClient {

    const val BASE_URL = "https://dummyjson.com/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service by lazy {
        retrofit.create(retrofitService::class.java)
    }


}