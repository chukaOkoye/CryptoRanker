package com.example.coinstask.ui.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.ListCoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ListCoinState {
    object Loading : ListCoinState()
    object Empty : ListCoinState()
    data class Success(val coin: List<CoinDto>) : ListCoinState()
    data class Error(val error: String) : ListCoinState()
}

class CoinListViewModel() : ViewModel() {

    private val repository: ListCoinRepository

    private val _listScreenState = MutableStateFlow<ListCoinState>(ListCoinState.Loading)
    val listScreenState = _listScreenState.asStateFlow()

    init {
        val apiService = ApiService.getInstance()
        val networkDataSource = NetworkDataSource(apiService)
        repository = ListCoinRepository(networkDataSource)
        loadCoins()
    }

    fun loadCoins() {
        viewModelScope.launch {
            try {
                _listScreenState.value = ListCoinState.Loading
                val coins = repository.getCoins()
                if(coins.isNotEmpty()){
                    _listScreenState.value = ListCoinState.Success(coins)
                }
                // To test if the coin value is present
                Log.d(TAG, "Coins: ${_listScreenState.value}")

            } catch (e: Exception) {
                _listScreenState.value = ListCoinState.Error(e.message.toString())
            }
        }
    }

}
