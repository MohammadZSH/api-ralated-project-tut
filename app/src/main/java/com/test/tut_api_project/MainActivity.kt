package com.test.tut_api_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.test.tut_api_project.screens.ItemDetailScreen
import com.test.tut_api_project.screens.MainScreen
import com.test.tut_api_project.ui.theme.ColorOfAPiApp
import com.test.tut_api_project.ui.theme.Tut_api_projectTheme
import com.test.tut_api_project.viewModel.ApiProjectViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: ApiProjectViewModel = viewModel()
            val navController = rememberNavController()
            Tut_api_projectTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ColorOfAPiApp.MainScreenColor)
                ) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = "main_screen"
                    ) {
                        composable("main_screen") {
                            MainScreen(
                                innerPadding,
                                viewModel,
                                navController
                            )
                        }
                        composable(
                            "item_details_screen/{Index}",
                            arguments = listOf(navArgument("Index") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val index = backStackEntry.arguments?.getInt("Index")

                            ItemDetailScreen(viewModel, navController,index)
                        }
                    }

                }
            }
        }
    }
}

