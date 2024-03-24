package com.example.coinstask.data.repository

import android.content.ContentValues
import android.util.Log
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class ListCoinRepository(
    private val networkDataSource: NetworkDataSource
    ) {
    suspend fun getCoins(): List<CoinDto> {
        return try {
            withContext(Dispatchers.IO) {
                networkDataSource.fetchCoins()
            }
        } catch (e: Exception){
            Log.e(ContentValues.TAG,"Retrofit exception coin list: ${e.message}")
            throw e
        }
    }
}