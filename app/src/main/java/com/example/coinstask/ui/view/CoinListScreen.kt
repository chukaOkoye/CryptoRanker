package com.example.coinstask.ui.view

import com.example.coinstask.ui.viewmodel.CoinListViewModel
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinstask.ui.viewmodel.ListCoinState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel,
    onCoinClick: (id: String) -> Unit ){

    val coinsState = viewModel.listScreenState.collectAsState().value

    // No LaunchedEffect to prevent constant reload

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = "CryptoRanker",
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp
                )
            })
            // Refresh button
            Button(
                onClick = {
                    // Refresh coins on button click
                    viewModel.loadCoins()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                if (coinsState is ListCoinState.Loading) {
                    CircularProgressIndicator(color = Color.White)
                } else {
                    Text("Refresh")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            when (coinsState) {
                is ListCoinState.Success -> {
                    LazyColumn {
                        items(coinsState.coin) { coin ->
                            Column(
                                modifier = Modifier.animateContentSize() // Animate item placement
                            ) {
                                Button(
                                    onClick = { onCoinClick(coin.id) },
                                    modifier = Modifier.padding(16.dp)
                                ) {
                                    Text("#${coin.rank}")
                                    Spacer(modifier = Modifier.padding(6.dp))
                                    Text(coin.name)
                                    Spacer(modifier = Modifier.padding(6.dp))
                                    Text(coin.symbol)
                                }
                            }
                            Divider(color = Color.Gray, thickness = 1.dp)
                        }
                    }
                }
                is ListCoinState.Error -> {
                    Text(
                        text = "No coins available: ${coinsState.error}",
                        style = androidx.compose.ui.text.TextStyle(
                            color = Color.Red,
                            fontSize = 18.sp
                        )
                    )
                }
                is ListCoinState.Loading -> {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    Text(
                        text = "Empty view",
                        modifier = Modifier.padding(16.dp),
                        style = androidx.compose.ui.text.TextStyle(
                            color = Color.Red,
                            fontSize = 18.sp
                        )
                    )
                }
            }
        }
    }
}