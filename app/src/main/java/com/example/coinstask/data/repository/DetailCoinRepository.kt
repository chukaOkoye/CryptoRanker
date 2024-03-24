package com.example.coinstask.data.repository

import android.content.ContentValues
import android.util.Log
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class DetailCoinRepository(private val networkDataSource: NetworkDataSource) {

    suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return try {
            withContext(Dispatchers.IO) {
                networkDataSource.fetchCoinDetails(coinId)
            }
        } catch (e: Exception) {
            Log.e(ContentValues.TAG, "Retrofit exception Coin details: ${e.message}")
            throw e
        }
    }
}