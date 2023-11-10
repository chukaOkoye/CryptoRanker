package com.example.coinstask.data.repository

import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import retrofit2.Response
import java.util.concurrent.Flow
import kotlin.Result.Companion.failure


class CoinRepository(
    private var networkDataSource: NetworkDataSource
    ) {
    suspend fun getCoins(): List<CoinDto> {
        return networkDataSource.fetchCoins()
    }

    suspend fun getCoinDetails(coinId: String): CoinDetailDto {
        return networkDataSource.fetchCoinDetails(coinId)
    }
}
