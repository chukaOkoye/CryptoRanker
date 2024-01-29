package com.example.coinstask.ui.viewmodel

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CoinViewModel : ViewModel() {

    private val repository: CoinRepository

    // Add sealed class for states
    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _coins = MutableLiveData<List<CoinDto>>()
    val coins = _coins

    private val _coinDetails = MutableLiveData<CoinDetailDto>()
    val coinDetails = _coinDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        val apiService = ApiService.getInstance()
        val networkDataSource = NetworkDataSource(apiService)
        repository = CoinRepository(networkDataSource)
        loadCoins()
    }

    fun loadCoins() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val coin = repository.getCoins()
                _coins.value = coin

                // To test if the coin value is present
                Log.d(TAG, "Coins: ${coins.value}")

            } catch (e: Exception) {
                _error.value = "Error fetching coins: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadCoinDetails(coinId:String) {
        viewModelScope.launch {
            try {
                val coin = repository.getCoinDetails(coinId)
                _coinDetails.value = coin

                // To test if the coin detail value is present
                Log.d(TAG, "Coin Detail: ${coinDetails.value}")

            } catch (e: Exception) {
                _error.value = "Error fetching coin details: ${e.message}"
            }
        }
    }

}
