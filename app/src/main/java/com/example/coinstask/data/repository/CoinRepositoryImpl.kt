package com.example.coinstask.data.repository

import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import java.net.HttpURLConnection

class CoinRepositoryImpl : CoinRepository {

    // Removed HTTPConnection for clarity
    private val apiService: ApiService = ApiService.getInstance()

    override suspend fun getCoins(): CoinDto {
        return apiService.getCoins()
    }

    override suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return apiService.getCoinDetails(coinId)    }
}
