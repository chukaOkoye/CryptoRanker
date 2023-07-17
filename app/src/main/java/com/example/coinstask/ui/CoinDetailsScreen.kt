package com.example.coinstask.ui

import CoinViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CoinDetailsScreen(coinViewModel: CoinViewModel,
                      coinId: String) {

    LaunchedEffect(true) {
        coinViewModel.getCoinDetails(coinId)
    }

    val coinDetailState by coinViewModel.coinDetails.observeAsState()

    Box(Modifier.fillMaxSize()) {
        coinDetailState?.let { coinDetail ->
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Description: ${coinDetail.description}")
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "ID: ${coinDetail.id}")
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = "Is Active: ${coinDetail.is_active}")
            }
        }
    }
}