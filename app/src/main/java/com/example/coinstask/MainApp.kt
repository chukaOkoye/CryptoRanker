package com.example.coinstask

import CoinViewModel
import android.app.Application
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coinstask.ui.view.CoinDetailsScreen
import com.example.coinstask.ui.view.CoinScreen

@Composable
fun MainApp() {
    val navController = rememberNavController()

    MaterialTheme {
        NavHost(navController = navController, startDestination = "/") {
            composable(route = "/") {
                CoinScreen(
                    viewModel(),
                    onCoinClick = { coinId ->
                        navController.navigate("/coin/$coinId")
                    })
            }

            composable(
                route = "/coin/{id}",
            ) { backStackEntry ->
                val coinId = backStackEntry.arguments?.getString("id")
                CoinDetailsScreen(
                    coinId = coinId ?: "",
                    navController = navController,
                    onBackClick = {
                        navController.popBackStack()
                    },
                    viewModel()
                )
            }
        }
    }
}