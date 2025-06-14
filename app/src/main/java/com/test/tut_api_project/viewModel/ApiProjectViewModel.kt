package com.test.tut_api_project.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.test.tut_api_project.models.recipe
import com.test.tut_api_project.models.recipesResponse
import com.test.tut_api_project.retrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiProjectViewModel(app: Application) : AndroidViewModel(app) {
    private var _listOfRecipes= MutableStateFlow(listOf<recipe>())
    val listOfRecipes = _listOfRecipes.asStateFlow()
    init {

        retrofitClient.service.getAll()?.let {
            it.enqueue(
                object : Callback<recipesResponse?>{
                    override fun onResponse(
                        call: Call<recipesResponse?>,
                        response: Response<recipesResponse?>
                    ) {
                        _listOfRecipes.value = response.body()!!.recipes
                    }
                    override fun onFailure(
                        call: Call<recipesResponse?>,
                        t: Throwable
                    ) {
                    }
                }
            )
        }
    }

}