package com.example.coinstask

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinstask.ui.view.CoinDetailsScreen
import com.example.coinstask.ui.view.CoinListScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "/") {
            composable(route = "/") {
                CoinListScreen(
                    viewModel(),
                    onCoinClick = { coinId ->
                        navController.navigate("/coin/$coinId")
                    })
            }

            composable(route = "/coin/{id}",) { backStackEntry ->
                val coinId = backStackEntry.arguments?.getString("id")
                CoinDetailsScreen(
                    coinId = coinId ?: "",
                    onBackClick = {
                        navController.popBackStack()
                    },
                    viewModel()
                )
            }
        }
    }
}