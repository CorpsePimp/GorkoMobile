package com.example.gorko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gorko.presentation.screens.LoginScreen
import com.example.gorko.presentation.screens.MainScreen
import com.example.gorko.presentation.screens.RegistrationScreen
import com.example.gorko.presentation.screens.TimeLineScreen
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.gorko.presentation.viewmodel.WeddingViewModel

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "login", modifier = modifier) {
        composable("login") {
            LoginScreen(
                navController = navController,
                onRegisterClick = { navController.navigate("registration") },
                onLoginClick = {navController.navigate("main")}
            )
        }
        composable("registration") {
            RegistrationScreen(
                navController = navController,
                onLoginClick = { navController.popBackStack("login", inclusive = false) }
            )
        }
        composable("main") {
            val weddingViewModel: WeddingViewModel = hiltViewModel()
            MainScreen(
                onTasksClick = { /* navController.navigate("tasks") */ },
                onTasksMoreClick = { /* navController.navigate("tasks") */ },
                onFinanceClick = { /* navController.navigate("finance") */ },
                onInspirationClick = { /* navController.navigate("inspiration") */ },
                onInspirationMoreClick = { /* navController.navigate("inspiration") */ },
                onTimelineClick = { navController.navigate("timeline") }
            )
            // composable("main") { MainScreen(navController) }
            // Добавляй остальные экраны по мере необходимости
        }
        composable("timeline") {
            val weddingViewModel: WeddingViewModel = hiltViewModel()
            TimeLineScreen(
                weddingViewModel = weddingViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
