package com.test.tut_api_project.models

data class recipe(
    val id : Int,
    val name: String,
    val image: String,
    val ingredients: List<String>,
    val instructions: List<String>,
    val difficulty: String,
    val cuisine: String,
    val rating: Float,
    val mealType: List<String>
)
