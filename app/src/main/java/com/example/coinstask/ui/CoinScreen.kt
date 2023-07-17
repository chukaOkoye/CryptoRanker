package com.example.coinstask.ui.views

import CoinViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CoinScreen(
    coinViewModel: CoinViewModel,
    onCoinClick: (id: String) -> Unit
) {
    LaunchedEffect(true) {
        coinViewModel.getCoins()
    }

    val coinsState by coinViewModel.coins.observeAsState()
    val showDialog = remember { mutableStateOf(false) }
    val selectedCoinId = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {
        if (coinsState != null) {
            if (coinsState!!.isNotEmpty()) {
                //val sortedCoins = coinsState!!.sortedBy { it.name }
                LazyColumn {
                    items(coinsState!!) { coin ->
                        Column {
                            Button(
                                onClick = { onCoinClick(coin.id) },
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(coin.name)
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(coin.symbol)
                                Spacer(modifier = Modifier.height(8.dp))
                            }
                        }
                        Divider(color = Color.Gray, thickness = 1.dp)
                    }
                }
            } else {
                Text(
                    text = "No coins available",
                    modifier = Modifier.align(Alignment.Center),
                    style = androidx.compose.ui.text.TextStyle(color = Color.Red, fontSize = 18.sp)
                )
            }
        }
    }


}





