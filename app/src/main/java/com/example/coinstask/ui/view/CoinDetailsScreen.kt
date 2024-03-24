package com.example.coinstask.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import com.example.coinstask.ui.viewmodel.CoinListViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.coinstask.R
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.repository.DetailCoinRepository
import com.example.coinstask.ui.viewmodel.CoinDetailViewModel
import com.example.coinstask.ui.viewmodel.DetailsCoinState
import org.jetbrains.annotations.Async


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
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(25.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context = LocalContext.current)
                            .data(state.coin.logo)
                            .crossfade(true)
                            .build(),
                        contentDescription = stringResource(R.string.coin_logo),
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(150.dp)
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Name: ${state.coin.name}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.padding(8.dp))
                    if (state.coin.description.isNotEmpty()) {
                        Text(text = "Description: ${state.coin.description}")
                        Spacer(modifier = Modifier.padding(8.dp))
                    }
                    Text(text = "Type: ${state.coin.type}")
                    Spacer(modifier = Modifier.padding(8.dp))
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Outlined.ArrowBack,
                            contentDescription = stringResource(R.string.back_button),
                            modifier = Modifier.size(50.dp)
                        )
                    }
                }
            }

            is DetailsCoinState.Error -> {
                Text(
                    text = "Error: ${state.error}",
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.Center),
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
                    text = stringResource(R.string.no_details_present),
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