package com.test.tut_api_project.screens

import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.test.tut_api_project.R
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
    val widthsOfDifficulty =
        if (listOfRecipes.value[index!!].difficulty == "Easy") 160.dp else if (listOfRecipes.value[index!!].difficulty == "Medium") 240.dp else 320.dp
    val colorsOfDifficulty =
        if (listOfRecipes.value[index!!].difficulty == "Easy") ColorOfAPiApp.EasyColor else if (listOfRecipes.value[index!!].difficulty == "Medium") ColorOfAPiApp.MediumColor else ColorOfAPiApp.HardColor
    val isBackButtonClicked by viewModel.isBackButtonClicked.collectAsState()
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
                Image(
                    painterResource(R.drawable.strawberry),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                )
                Row(
                    Modifier
                        .padding(top = 35.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton({
                        if (!isBackButtonClicked) {
                            viewModel.isBackButtonClickedSetter(true)
                            navController.popBackStack("main_screen", false)
                        }
                    }, modifier = Modifier.size(70.dp)) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "back", modifier = Modifier.size(40.dp))
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
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
                    Row {
                        IconButton({}, modifier = Modifier.size(70.dp)) {
                            Box(
                                contentAlignment = Alignment.TopCenter
                            ) {
                                Box(
                                    Modifier
                                        .size(10.dp)
                                        .background(color = Color.Red, shape = CircleShape)
                                )
                                Icon(
                                    Icons.Default.Edit,
                                    contentDescription = null,
                                    modifier = Modifier.size(40.dp)
                                )
                            }
                        }
                        IconButton({}, modifier = Modifier.size(70.dp)) {
                            Icon(
                                Icons.Default.FavoriteBorder,
                                contentDescription = null,
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        @Composable
        fun StarRating(
            rating: Float,
            maxStars: Int = 5,
            starSize: Dp = 24.dp,
            filledColor: Color = Color.Yellow,
            emptyColor: Color = Color.LightGray,
        ) {
//            require(rating in 0f..maxStars.toFloat()) {
//                "Rating must be between 0 and $maxStars"
//            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                // Stars display
                repeat(maxStars) { index ->
                    val starValue = rating - index
                    val modifier = Modifier.size(starSize)

                    when {
                        starValue >= 0.75f -> {
                            // Full star
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = "Full star",
                                tint = filledColor,
                                modifier = modifier
                            )
                        }

                        starValue >= 0.25f -> {
                            // Half star
                            Icon(
                                painter = painterResource(R.drawable.baseline_star_half_24),
                                contentDescription = "Half star",
                                tint = filledColor,
                                modifier = modifier
                            )
                        }

                        else -> {
                            // Empty star
                            Icon(
                                imageVector = Icons.Outlined.Star,
                                contentDescription = "Empty star",
                                tint = emptyColor,
                                modifier = modifier
                            )
                        }
                    }
                }
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(start = 10.dp, top = 1.dp),
        ) {
            item {
                Card(
                    Modifier
                        .padding(bottom = 10.dp)
                        .width(320.dp)
                        .drawWithContent {
                            drawContent()
                            drawRect(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        ColorOfAPiApp.StarlyColor
                                    ),
                                    startX = size.width,
                                    endX = size.width - size.width
                                ),
                                alpha = 0.6f
                            )
                        }
                ) {
                    Row {
                        Text(
                            buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                ) {
                                    append("Ratings: ")
                                }
                                withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                    append(listOfRecipes.value[index!!].rating.toString())
                                }
                            }
                        )
                        StarRating(listOfRecipes.value[index!!].rating)
                    }
                }
            }
            item {
                Card(
                    Modifier
                        .padding(bottom = 10.dp)
                        .width(widthsOfDifficulty),
                    colors = CardDefaults.cardColors(containerColor = colorsOfDifficulty)
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Difficulty: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append(listOfRecipes.value[index!!].difficulty)
                            }
                        }
                    )
                }
            }
            item {
                Card(
                    Modifier.padding(bottom = 10.dp)
                ) {
                    Text(
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("Cuisine: ")
                            }
                            withStyle(style = SpanStyle(fontSize = 18.sp)) {
                                append(listOfRecipes.value[index!!].cuisine)
                            }
                        }
                    )
                }
            }

            item {
                Card(
                    Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Meal Type", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            items(listOfRecipes.value[index!!].mealType.size) {
                Card(
                    Modifier.padding(bottom = 5.dp)
                ) {
                    Text(listOfRecipes.value[index!!].mealType[it])
                }
            }
            item {
                Card(
                    Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Ingredients", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            items(listOfRecipes.value[index!!].ingredients.size) {
                Card(
                    Modifier.padding(bottom = 5.dp)
                ) {
                    Text("${it + 1} : ${listOfRecipes.value[index!!].ingredients[it]}")
                }
            }
            item {
                Card(
                    Modifier.padding(bottom = 10.dp)
                ) {
                    Text("Instructions", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                }
            }
            items(listOfRecipes.value[index!!].instructions.size) {
                Card(
                    Modifier.padding(bottom = 5.dp)
                ) {
                    Text("Step ${it + 1} : ${listOfRecipes.value[index!!].instructions[it]}")
                }
            }
        }
    }


}