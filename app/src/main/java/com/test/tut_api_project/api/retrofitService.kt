package com.test.tut_api_project.api

import com.test.tut_api_project.models.recipesResponse
import retrofit2.Call
import retrofit2.http.GET



interface retrofitService {
    @GET("recipes")
    fun getAll(): Call<recipesResponse?>?
}