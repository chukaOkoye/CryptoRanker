package com.example.coinstask.data.repository

import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.util.concurrent.Flow
import kotlin.Result.Companion.failure


class CoinRepository(
    private var networkDataSource: NetworkDataSource
    ) {
    suspend fun getCoins(): List<CoinDto> {
       return withContext(Dispatchers.Default){
            networkDataSource.fetchCoins()
        }
    }

    suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return withContext(Dispatchers.Default){
            networkDataSource.fetchCoinDetails(coinId)
        }
    }
}
