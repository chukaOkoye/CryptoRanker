import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.coinstask.data.api.ApiService
import com.example.coinstask.data.api.NetworkDataSource
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepository
import kotlinx.coroutines.launch

class CoinViewModel (application: Application
    ) : AndroidViewModel(application) {

    private val repository: CoinRepository

    private var _coins = MutableLiveData<List<CoinDto>>()
    val coins = _coins

    private val _coinDetails = MutableLiveData<CoinDetailDto>()
    val coinDetails = _coinDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    init {
        val apiService = ApiService.getInstance()
        val networkDataSource = NetworkDataSource(apiService)
        repository = CoinRepository(networkDataSource)
    }

    fun loadCoins() {
        viewModelScope.launch {
            try {
                val coin = repository.getCoins()
                coins.value = coin

                // To test if the coin value is present
                Log.d(TAG, "Coins: ${coins.value}")

            } catch (e: Exception) {
                _error.value = "Error fetching coins: ${e.message}"
            }
        }
    }

    fun loadCoinDetails(coinId:String) {
        viewModelScope.launch {
            try {
                val coin = repository.getCoinDetails(coinId)
                coinDetails.value = coin

                // To test if the coin detail value is present
                Log.d(TAG, "Coin Detail: ${coinDetails.value}")

            } catch (e: Exception) {
                _error.value = "Error fetching coin details: ${e.message}"
            }
        }
    }
}
