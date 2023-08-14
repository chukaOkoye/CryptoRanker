package com.example.coinstask.data.repository

import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import kotlin.Result.Companion.failure


class CoinRepository(
    private val apiService: ApiService = ApiService.getInstance()
    ) {
    suspend fun getCoins(): Result<List<CoinDto>> {
        return try {
            val coins = apiService.getCoins()
            Result.success(coins)
        } catch (e: Exception){
            failure(e)
        }
    }

    suspend fun getCoinDetails(coinId: String): Result<CoinDetailDto> {
        return try {
            val coinDetail = apiService.getCoinDetails(coinId)
            Result.success(coinDetail)
        } catch (e: Exception) {
            failure(e)
        }
    }
}
