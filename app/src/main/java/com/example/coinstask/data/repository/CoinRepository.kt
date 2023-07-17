package com.example.coinstask.data.repository

import android.util.Log
import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto


class CoinRepository(
    private val apiService: ApiService
    ) {
    suspend fun getCoins(): Result<List<CoinDto>> {
        return try {
            val coins = apiService.getCoins()
            // Testing the response
            Log.d("CoinRepository", "getCoins() response: $coins")
            Result.success(coins)
        } catch (e: Exception){
            Result.failure(e)
        }
    }

    suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return apiService.getCoinDetails(coinId)
    }
}
