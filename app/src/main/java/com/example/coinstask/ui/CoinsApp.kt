package com.example.coinstask.ui

import CoinViewModel
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinstask.data.repository.CoinRepositoryImpl

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
                    coinViewModel = CoinViewModel(coinRepository = CoinRepositoryImpl()),
                    onCoinClick = { id -> navController.navigate("/coin/$id")
                })
            }

            composable(
                route = "/coin/{id}",
            ) {
                // Add CoinDetailsScreen
            }
        }
    }
}