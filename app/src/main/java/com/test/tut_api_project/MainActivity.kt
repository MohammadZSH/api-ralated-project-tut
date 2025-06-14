package com.test.tut_api_project

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.test.tut_api_project.models.recipesResponse
import com.test.tut_api_project.ui.theme.Tut_api_projectTheme
import com.test.tut_api_project.viewModel.ApiProjectViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ApiProjectViewModel = viewModel()
            val listOfRecipes = viewModel.listOfRecipes.collectAsState()
            Tut_api_projectTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LazyColumn(
                        Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        items(listOfRecipes.value.size) { index ->
                            Card(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(20.dp)
                                    .background(Color.Gray)
                            ) {
                                Text("id: ${listOfRecipes.value[index].id}")
                                Spacer(Modifier.size(40.dp))
                                Text("name: ${listOfRecipes.value[index].name}")
                                Spacer(Modifier.size(40.dp))
                                AsyncImage(
                                    model = listOfRecipes.value[index].image,
                                    contentDescription = null,
                                )
                            }
                            Spacer(Modifier.size(40.dp))
                        }
                    }
                }
            }
        }
    }
}

