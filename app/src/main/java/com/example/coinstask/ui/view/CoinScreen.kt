package com.example.coinstask.ui.view

import CoinViewModel
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coinstask.data.dto.CoinDto

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinScreen(
    onCoinClick: (id: String) -> Unit ){

    val viewModel: CoinViewModel = hiltViewModel()
    LaunchedEffect(true) {
        viewModel.fetchCoins()
    }

    val coinsState by viewModel.coins.observeAsState()
    val showDialog = remember { mutableStateOf(false) }
    val selectedCoinId = remember { mutableStateOf("") }

    Box(Modifier.fillMaxSize()) {
        Column(Modifier.padding(top = 16.dp), horizontalAlignment = Alignment.CenterHorizontally) {

            // Refresh button
            Button(
                onClick = {
                    viewModel.fetchCoins() // Refresh coins on button click
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Refresh")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (coinsState != null) {
                if (coinsState!!.isNotEmpty()) {
                    LazyColumn {
                        items(coinsState!!) { coin ->
                            Column(
                                modifier = Modifier.animateContentSize() // Animate item placement
                            ) {
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
                        style = androidx.compose.ui.text.TextStyle(
                            color = Color.Red,
                            fontSize = 18.sp
                        )
                    )
                }
            } else {
                val errorState by viewModel.error.observeAsState()
                if (errorState != null) {
                    Text(
                        text = "Error: $errorState",
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        style = androidx.compose.ui.text.TextStyle(color = Color.Red, fontSize = 18.sp)
                    )
                }
            }
        }
    }
}