package com.example.ipz

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun PostcardApp() {
    val navController = rememberNavController()

    var postcardText by remember { mutableStateOf("") }
    var backgroundColor by remember { mutableStateOf(Color.White) }
    var selectedImage by remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onStartClick = {
                    navController.navigate("text_input")
                }
            )
        }

        composable("text_input") {
            TextInputScreen(
                initialText = postcardText,
                onNextClick = { text ->
                    postcardText = text
                    navController.navigate("color_selection")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("color_selection") {
            ColorSelectionScreen(
                initialColor = backgroundColor,
                onNextClick = { color ->
                    backgroundColor = color
                    navController.navigate("image_selection")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("image_selection") {
            ImageSelectionScreen(
                initialImage = selectedImage,
                onNextClick = { image ->
                    selectedImage = image
                    navController.navigate("result")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("result") {
            ResultScreen(
                text = postcardText,
                backgroundColor = backgroundColor,
                selectedImage = selectedImage,
                onBackClick = {
                    postcardText = ""
                    backgroundColor = Color.White
                    selectedImage = ""
                    navController.popBackStack("welcome", inclusive = false)
                },
                onRestartClick = {
                    postcardText = ""
                    backgroundColor = Color.White
                    selectedImage = ""
                    navController.navigate("text_input") {
                        popUpTo("welcome")
                    }
                }
            )
        }
    }
}