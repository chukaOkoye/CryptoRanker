package com.example.coinstask

import com.example.coinstask.viewmodel.CoinViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.CoinRepository
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CoinViewModelTest {

    @Mock
    private lateinit var coinRepository: CoinRepository

    private lateinit var coinViewModel: CoinViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        coinViewModel = CoinViewModel(coinRepository)
    }

    @Test
    fun `test getCoins retrieves and sets coins value`()  {
        // Create a mock CoinRepository
        val coinRepository = mock(CoinRepository::class.java)

        // Create a list of coins
        val coins = listOf(
            CoinDto(
                name = "BTC",
                symbol = "Bitcoin",
                rank = 1,
                id = "btc-bitcoin",
                is_active = true,
                is_new = true,
                type = "crypto"
            ),
            CoinDto(
                name = "ETH",
                symbol = "Ethereum",
                rank = 2,
                id = "eth-ethereum",
                is_active = true,
                is_new = true,
                type = "crypto"
            ),
            CoinDto(
                name = "XRP",
                symbol = "Ripple",
                rank = 3,
                id = "xrp-ripple",
                is_active = true,
                is_new = true,
                type = "crypto"
            )
        )

        // Mock the repository to return Result.success(coins)
        `when`(coinRepository.getCoins()).thenReturn(Result.success(coins))

        // Create the com.example.coinstask.ui.viewmodel.CoinViewModel with the mocked repository
        val coinViewModel = CoinViewModel(coinRepository)

        // Call the getCoins() method within a coroutine scope
        coinViewModel.getCoins()

        // Observe the coins value and assert its contents
        coinViewModel.coins.observeOnce { observedCoins ->
            assertEquals(coins, observedCoins)
        }
    }

}