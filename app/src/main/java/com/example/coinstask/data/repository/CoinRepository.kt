package com.example.coinstask.data.repository

import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import java.net.HttpURLConnection

interface CoinRepository {
    suspend fun getCoins(): CoinDto
    suspend fun getCoinDetails(coinId: String): CoinDetailDto
}
