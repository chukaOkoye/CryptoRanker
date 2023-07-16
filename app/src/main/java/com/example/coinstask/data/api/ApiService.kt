package com.example.coinstask.data.api

import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/coins")
    suspend fun getCoins(): List<CoinDto>
    @GET("/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId") coinId: String): CoinDetailDto

    companion object {
        private var apiService:ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null){
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.coinpaprika.com/v1")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}
