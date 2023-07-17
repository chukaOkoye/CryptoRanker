package com.example.coinstask.ui

import CoinViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.repository.CoinRepository

@Composable
fun CoinsApp() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(
            navController,
            "/"
        ) {
            composable(route = "/") {
                CoinScreen(
                    coinViewModel = CoinViewModel(coinRepository = CoinRepository(apiService =
                    ApiService.getInstance())),
                    onCoinClick = { coinId ->
                        // Handle the click event for the coin
                        navController.navigate("/coin/$coinId")
                    })
            }

            composable(route = "/coin/{id}") { backStackEntry ->
                val coinId = backStackEntry.arguments?.getString("id")
                CoinDetailsScreen(coinId = coinId ?: "",
                    coinViewModel = CoinViewModel(coinRepository = CoinRepository(apiService =
                    ApiService.getInstance())))
            }
        }
    }
}