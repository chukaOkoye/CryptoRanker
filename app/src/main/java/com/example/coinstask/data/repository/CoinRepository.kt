package com.example.coinstask.data.repository

import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class CoinRepository(
    private var networkDataSource: NetworkDataSource
    ) {
    suspend fun getCoins(): List<CoinDto> {
        if (networkDataSource.fetchCoins().isEmpty()) {
            error("Error fetching Coins. Do you have an internet connection?")
        } else {
            return withContext(Dispatchers.IO) {
                networkDataSource.fetchCoins()
            }
        }
    }
    suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return if(networkDataSource.fetchCoinDetails(coinId).is_active){
            withContext(Dispatchers.IO) {
                networkDataSource.fetchCoinDetails(coinId)
            }
        } else {
            error("Error fetching Coin details. Do you have an internet connection?")
        }
    }
}