package com.example.coinstask.data.api

import android.content.ContentValues.TAG
import android.util.Log
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.logging.Logger
import com.example.coinstask.logging.VoidLogger
import retrofit2.Response
import java.io.IOException
import kotlin.Result.Companion.failure

class NetworkDataSource(
    private val apiService: ApiService,
    ) {
    suspend fun fetchCoins(): List<CoinDto> {
        try {
            return apiService.getCoins()
        } catch (e: IOException){
            Log.e(TAG,"Exception from retrofit: `${e.message}`.")
            throw FetchCoinsException("Failed to fetch coins", e)
        }
    }

    suspend fun fetchCoinDetails(coinId: String): CoinDetailDto {
        try {
            return apiService.getCoinDetails(coinId)
        } catch (e: Exception){
            Log.e(TAG,"Exception from retrofit ${e.message}.")
            throw FetchCoinDetailsException("Failed to fetch coin details", e)

        }
    }
}

// Custom exceptions
class FetchCoinsException(message: String, cause: Throwable? = null) : Exception(message, cause)
class FetchCoinDetailsException(message: String, cause: Throwable) : Exception(message, cause)