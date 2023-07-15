import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinstask.data.dto.CoinDetailDto
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepository
import kotlinx.coroutines.launch

class CoinViewModel(private val coinRepository: CoinRepository) : ViewModel() {

    private val _coins = MutableLiveData<List<CoinDto>>()
    val coins: LiveData<List<CoinDto>> get() = _coins

    private val _coinDetails = MutableLiveData<CoinDetailDto>()
    val coinDetails: LiveData<CoinDetailDto> get() = _coinDetails

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun getCoins() {
        viewModelScope.launch {
            try {
                val coinList = coinRepository.getCoins()
                _coins.value = listOf(coinList)
            } catch (e: Exception) {
                _error.value = "Error fetching coins: ${e.message}"
            }
        }
    }

    fun getCoinDetails(coinId: String) {
        viewModelScope.launch {
            try {
                val coinDetail = coinRepository.getCoinDetails(coinId)
                _coinDetails.value = coinDetail
            } catch (e: Exception) {
                _error.value = "Error fetching coin details: ${e.message}"
            }
        }
    }
}
