package com.example.coinstask.ui.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.DetailCoinRepository
import com.example.coinstask.data.repository.ListCoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


sealed class DetailsCoinState {
    object Empty : DetailsCoinState()
    object Loading : DetailsCoinState()
    data class Success(val coin: CoinDetailDto) : DetailsCoinState()
    data class Error(val error: String) : DetailsCoinState()
}

class CoinDetailViewModel: ViewModel() {
    private val repository: DetailCoinRepository

    private val _detailScreenState = MutableStateFlow<DetailsCoinState>(DetailsCoinState.Empty)
    val detailScreenState = _detailScreenState.asStateFlow()

    init {
        val apiService = ApiService.getInstance()
        val networkDataSource = NetworkDataSource(apiService)
        repository = DetailCoinRepository(networkDataSource)
    }

    fun loadCoinDetails(coinId:String) {
        viewModelScope.launch {
            _detailScreenState.value = DetailsCoinState.Loading
            try {
                val coin = repository.getCoinDetails(coinId)
                _detailScreenState.value = DetailsCoinState.Success(coin)

                // To test if the coin detail value is present
                Log.d(ContentValues.TAG, "Coin Detail: ${(DetailsCoinState.Success(coin)).coin}")

            } catch (e: Exception) {
                _detailScreenState.value = DetailsCoinState.Error(e.message.toString())
            }
        }

    }
}