package com.example.gorko.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gorko.presentation.screens.FinanceScreen
import com.example.gorko.presentation.screens.InspirationScreen
import com.example.gorko.presentation.screens.LoginScreen
import com.example.gorko.presentation.screens.MainScreen
import com.example.gorko.presentation.screens.RegistrationScreen
import com.example.gorko.presentation.screens.SettingsScreen
import com.example.gorko.presentation.screens.TaskScreen
import com.example.gorko.presentation.screens.TimeLineScreen
import com.example.gorko.presentation.screens.VendorsScreen
import com.example.gorko.presentation.viewmodel.FinanceViewModel
import com.example.gorko.presentation.viewmodel.WeddingViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AppNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val weddingViewModel: WeddingViewModel = hiltViewModel()
    val financeViewModel: FinanceViewModel = hiltViewModel()
    val expensesState = financeViewModel.expenses.collectAsState()
    val spent = expensesState.value.sumOf { it.amount }
    val currencyFormat = NumberFormat.getNumberInstance(Locale("ru", "RU")).apply { maximumFractionDigits = 0 }
    val totalSpentString = "${currencyFormat.format(spent)} ₽"

    NavHost(navController = navController, startDestination = "login", modifier = modifier) {
        composable("login") {
            LoginScreen(
                navController = navController,
                onRegisterClick = { navController.navigate("registration") },
                onLoginClick = { navController.navigate("main") }
            )
        }
        composable("registration") {
            RegistrationScreen(
                navController = navController,
                onLoginClick = { navController.popBackStack("login", inclusive = false) }
            )
        }
        composable("main") {
            MainScreen(
                weddingViewModel = weddingViewModel,
                onTasksClick = { navController.navigate("tasks") },
                onTasksMoreClick = { navController.navigate("tasks") },
                onFinanceClick = { navController.navigate("finance") },
                onInspirationClick = {  navController.navigate("inspiration")  },
                onInspirationMoreClick = { /* navController.navigate("inspiration") */ },
                onTimelineClick = { navController.navigate("timeline") },
                onSettingsClick = { navController.navigate("settings") },
                onVendorsClick = { navController.navigate("vendors") },
                totalSpent = totalSpentString // теперь сумма всегда актуальна!
            )
        }
        composable("timeline") {
            TimeLineScreen(
                weddingViewModel = weddingViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("finance") {
            FinanceScreen(
                financeViewModel = financeViewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("inspiration") {
            InspirationScreen(onBack = { navController.popBackStack() })
        }
        composable("tasks") {
            TaskScreen(onBack = { navController.popBackStack() })
        }
        composable("settings") {
            SettingsScreen(
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("main") { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onMainClick = { navController.navigate("main") { launchSingleTop = true } }
            )
        }
        composable("vendors") {
            VendorsScreen(
                onHomeClick = { navController.navigate("main") },
                onSettingsClick = { navController.navigate("settings") },
                onVendorsClick = { /* ничего, т.к. уже тут */ }
            )
        }
    }
}