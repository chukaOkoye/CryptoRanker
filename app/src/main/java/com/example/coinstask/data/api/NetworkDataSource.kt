package com.example.coinstask.data.api

import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.logging.Logger
import com.example.coinstask.logging.VoidLogger
import retrofit2.Response
import kotlin.Result.Companion.failure

class NetworkDataSource(
    private val apiService: ApiService,
    private val logger: Logger = VoidLogger
    ) {
    suspend fun fetchCoins(): List<CoinDto> {
        try {
            return apiService.getCoins()
        } catch (e: Exception){
            logger.d("Exception from retrofit ${e.message}.")
            throw FetchCoinsException("Failed to fetch coins", e)
        }
    }

    suspend fun fetchCoinDetails(coinId: String): CoinDetailDto {
        try {
            return apiService.getCoinDetails(coinId)
        } catch (e: Exception){
            logger.d("Exception from retrofit ${e.message}.")
            throw FetchCoinDetailsException("Failed to fetch coin details", e)

        }
    }
}

// Custom exceptions
class FetchCoinsException(message: String, cause: Throwable? = null) : Exception(message, cause)
class FetchCoinDetailsException(message: String, cause: Throwable) : Exception(message, cause)