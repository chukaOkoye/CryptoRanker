package com.example.coinstask.ui.view

import android.widget.Toast
import com.example.coinstask.ui.viewmodel.CoinListViewModel
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinstask.ui.viewmodel.ListCoinState


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CoinListScreen(
    viewModel: CoinListViewModel,
    onCoinClick: (id: String) -> Unit ){

    val coinsState = viewModel.listScreenState.collectAsState().value
    val context = LocalContext.current

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
                    // Show toast that refresh was successful
//                    Toast.makeText(context, "Coins Load successful!", Toast.LENGTH_SHORT).show()
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            when (coinsState) {
                is ListCoinState.Success -> {
                    LazyColumn {
                        items(coinsState.coin) { coin ->
                            Column(
                                modifier = Modifier.animateItemPlacement()  // Animate item placement
                            ) {
                                FilledTonalButton(
                                    onClick = { onCoinClick(coin.id) },
                                    modifier = Modifier.padding(16.dp)
                                        .animateItemPlacement()
                                ) {
                                    Text(
                                        text = "#${coin.rank}  ${coin.name}  (${coin.symbol})",
                                        style = MaterialTheme.typography.bodyLarge,
                                        fontWeight = Bold)
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
                        Text(text = "Loading...",
                            style = MaterialTheme.typography.displaySmall)
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

