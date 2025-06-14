package com.test.tut_api_project.screens

import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.tut_api_project.ui.theme.ColorOfAPiApp
import com.test.tut_api_project.viewModel.ApiProjectViewModel

@Composable
fun ItemDetailScreen(
    viewModel: ApiProjectViewModel,
    navController: NavHostController,
    index: Int?
) {
    BackHandler {
        navController.popBackStack()
    }
    val listOfRecipes = viewModel.listOfRecipes.collectAsState()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(top = 35.dp)
//                    .background(ColorOfAPiApp.CardImageColor)
                    .drawWithContent {
                        drawContent()
                        // Draw gradient overlay to fade to blur
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    ColorOfAPiApp.BlurredShapeColor2
                                ),
                                startY = size.height,
                                endY = size.height - size.height
                            ),
                            alpha = 0.7f
                        )
                    }
            ) {

                Row(
                    Modifier
                        .padding(top = 35.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton({
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back")
                    }
                    Spacer(Modifier.size(20.dp))
                    Text(
                        "${listOfRecipes.value[index!!].name} Recipe",
                        style = TextStyle(
                            color = ColorOfAPiApp.NameOfFoodColor,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.ExtraBold,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.7f),
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            ),

                            )
                    )

                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text("Ratings: ", fontSize = 20.sp)
                Text(listOfRecipes.value[index!!].rating.toString())
            }
            item {
                Text("Difficulty: ", fontSize = 20.sp)
                Text(listOfRecipes.value[index!!].difficulty)
            }
            item {
                Text("Cuisine: ", fontSize = 20.sp)
                Text(listOfRecipes.value[index!!].cuisine)
            }
            item {
                Text("Meal Type:", fontSize = 20.sp)
            }
            items(listOfRecipes.value[index!!].mealType.size) {
                Text(listOfRecipes.value[index!!].mealType[it])
            }
            item {
                Text("Ingredients: ", fontSize = 20.sp)
            }
            items(listOfRecipes.value[index!!].ingredients.size) {
                Text("${it+1} : ${listOfRecipes.value[index!!].ingredients[it]}")
            }
            item {
                Text("Instructions: ", fontSize = 20.sp)
            }
            items(listOfRecipes.value[index!!].instructions.size) {
                Text("Step ${it+1} : ${listOfRecipes.value[index!!].instructions[it]}")
            }
        }
    }


}