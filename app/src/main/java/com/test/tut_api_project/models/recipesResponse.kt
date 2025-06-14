package com.test.tut_api_project.models

data class recipesResponse(
    val recipes: List<recipe>,
    val total: Int,
    val limit: Int
)
