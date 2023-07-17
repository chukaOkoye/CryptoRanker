import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepository
import kotlinx.coroutines.launch

class CoinViewModel(private val coinRepository: CoinRepository) : ViewModel() {

    private var _coins = MutableLiveData<List<CoinDto>>()
    val coins = _coins

    private val _coinDetails = MutableLiveData<CoinDetailDto>()
    val coinDetails = _coinDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        getCoins()
    }

    fun getCoins() {
        viewModelScope.launch {
            try {
                val coinResult = coinRepository.getCoins()
                if (coinResult.isSuccess) {
                    coins.value = coinResult.getOrThrow()
                    // To test if the coin value is present
                    Log.d(TAG, "Coins: ${coins.value}")
                } else {
                    _error.value = "Error fetching coins: ${coinResult.exceptionOrNull()?.message}"
                }
            } catch (e: Exception) {
                _error.value = "Error fetching coins: ${e.message}"
            }
        }
    }

    fun getCoinDetails(coinId:String) {
        viewModelScope.launch {
            try {
                val coinDetailResult = coinRepository.getCoinDetails(coinId)
                // To test if the coin detail value is present
                if (coinDetailResult.isSuccess){
                    coinDetails.value = coinDetailResult.getOrThrow()
                    Log.d(TAG, "Coin Detail: ${coinDetails.value}")
                }
            } catch (e: Exception) {
                _error.value = "Error fetching coin details: ${e.message}"
            }
        }
    }
}
