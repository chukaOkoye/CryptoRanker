package com.example.coinstask.viewmodel

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
import com.example.coinstask.data.api.FetchCoinDetailsException
import com.example.coinstask.data.api.FetchCoinsException
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ListCoinState {
    object Loading : ListCoinState()
    data class Success(val coin: List<CoinDto>) : ListCoinState()
    data class Error(val error: FetchCoinsException) : ListCoinState()
}
class CoinViewModel : ViewModel() {

    private val _screenState = MutableStateFlow<ListCoinState>(ListCoinState.Loading)
    val screenState = _screenState.asStateFlow()

    private val repository: CoinRepository

    private val _coinDetails = MutableStateFlow(CoinDetailDto(description = "",
        id = "", is_active = true, name =  "", type = ""))
    val coinDetails = _coinDetails.asStateFlow()

//    private val _error = MutableLiveData<String>()
//    val error: LiveData<String> = _error

    init {
        val apiService = ApiService.getInstance()
        val networkDataSource = NetworkDataSource(apiService)
        repository = CoinRepository(networkDataSource)
        loadCoins()
    }

    fun loadCoins() {
        viewModelScope.launch {
            try {
                _screenState.value = ListCoinState.Loading
                val coins = repository.getCoins()
                if(coins.isNotEmpty()){
                    _screenState.value = ListCoinState.Success(coins)
                }
                // To test if the coin value is present
                Log.d(TAG, "Coins: ${_screenState.value}")

            } catch (e: FetchCoinsException) {
                _screenState.value = ListCoinState.Error(e)
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

            } catch (e: FetchCoinsException) {
                _screenState.value = ListCoinState.Error(e)
//                _error.value = "Error fetching coin details: ${e.message}"
            }
        }
    }

}
