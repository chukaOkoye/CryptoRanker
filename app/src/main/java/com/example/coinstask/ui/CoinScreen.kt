package com.example.coinstask.ui

import CoinViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepositoryImpl

@Composable
fun CoinScreen(coinViewModel: CoinViewModel,
               onCoinClick: (id: String) -> Unit
    ) {
    LaunchedEffect(true) {
        coinViewModel.getCoins()
    }

    val coinsState = remember { coinViewModel.coins.value }

    Box(Modifier.fillMaxSize()) {
        when {
            coinsState.isNullOrEmpty() -> {
                Text("No coins available")
            }
            coinsState.isNotEmpty() -> {
                LazyColumn {
                    itemsIndexed(coinsState) { index, coinState ->
                        Button(
                            onClick = { onCoinClick(coinState.id) },
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Column {
                                Text(coinState.name)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(coinState.symbol)
                            }
                        }
                        if (index < coinsState.size - 1) {
                            Divider(color = Color.Gray, thickness = 1.dp)
                        }
                    }
                }
            }
        }
    }
}