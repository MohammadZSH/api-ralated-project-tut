package com.test.tut_api_project.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.test.tut_api_project.R
import com.test.tut_api_project.ui.theme.ColorOfAPiApp
import com.test.tut_api_project.viewModel.ApiProjectViewModel

@Composable
fun MainScreen(
    innerPadding: PaddingValues,
    viewModel: ApiProjectViewModel,
    navController: NavHostController
) {
    val listOfRecipes = viewModel.listOfRecipes.collectAsState()
    val listStateOfMainScreen = viewModel.listStateOfMainScreen
    LazyColumn(
        state = listStateOfMainScreen,
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
    ) {
        items(listOfRecipes.value.size) { index ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .clickable(onClick = {
                        viewModel.isBackButtonClickedSetter(false)
                        navController.navigate("item_details_screen/$index")
                    }),
//                colors = CardDefaults.cardColors(containerColor = ColorOfAPiApp.CardImageColor),
                elevation = CardDefaults.cardElevation(
                    10.dp,
                    50.dp,
                    20.dp,
                    20.dp,
                    0.dp
                ),
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(270.dp),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    val blurHeight = 100.dp
//                    AsyncImage(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .drawWithContent {
//                                drawContent()
//                                // Draw gradient overlay to fade to blur
//                                drawRect(
//                                    brush = Brush.verticalGradient(
//                                        colors = listOf(
//                                            Color.Transparent,
//                                            ColorOfAPiApp.BlurredShapeColor
//                                        ),
//                                        startY = size.height - blurHeight.toPx() * 2,
//                                        endY = size.height
//                                    ),
//                                    alpha = 0.8f
//                                )
//                            },
//                        model = listOfRecipes.value[index].image,
//                        contentDescription = null,
//
//                        )
                    Image(
                        painterResource(R.drawable.strawberry),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .drawWithContent {
                                drawContent()
                                // Draw gradient overlay to fade to blur
                                drawRect(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(
                                            Color.Transparent,
                                            ColorOfAPiApp.BlurredShapeColor
                                        ),
                                        startY = size.height - blurHeight.toPx() * 2,
                                        endY = size.height
                                    ),
                                    alpha = 0.8f
                                )
                            })
                    Text(
                        text = listOfRecipes.value[index].name,
                        style = TextStyle(
                            color = ColorOfAPiApp.NameOfFoodColor,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.SemiBold,
                            shadow = Shadow(
                                color = Color.White.copy(alpha = 0.7f),
                                offset = Offset(2f, 2f),
                                blurRadius = 4f
                            )
                        ),
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                }
            }
            Spacer(Modifier.size(40.dp))
        }
    }
}