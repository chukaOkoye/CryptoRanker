package com.example.coinstask.ui.view

import com.example.coinstask.ui.viewmodel.CoinListViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coinstask.data.repository.DetailCoinRepository
import com.example.coinstask.ui.viewmodel.CoinDetailViewModel
import com.example.coinstask.ui.viewmodel.DetailsCoinState

@Composable
fun CoinDetailsScreen(
    coinId: String,
    onBackClick: () -> Unit,
    viewModel: CoinDetailViewModel
) {
    val coinDetailState by viewModel.detailScreenState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCoinDetails(coinId)
    }

    Box(Modifier.fillMaxSize()) {
        when (val state = coinDetailState) {
            is DetailsCoinState.Success -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Name: ${state.coin.name}")
                    Spacer(modifier = Modifier.padding(8.dp))
                    if (state.coin.description.isNotEmpty()) {
                        Text(text = "Description: ${state.coin.description}")
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                    Text(text = "Type: ${state.coin.type}")
                    Spacer(modifier = Modifier.padding(8.dp))
                    Text(text = "Is Active: ${state.coin.is_active}")
                    Spacer(modifier = Modifier.padding(8.dp))
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Outlined.ArrowBack, contentDescription = "Back")
                    }
                }
            }

            is DetailsCoinState.Error -> {
                Text(
                    text = "Error: ${state.error}",
                    modifier = Modifier.padding(16.dp),
                    style = androidx.compose.ui.text.TextStyle(
                        color = Color.Red,
                        fontSize = 18.sp
                    )
                )
            }

            is DetailsCoinState.Loading -> {
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
