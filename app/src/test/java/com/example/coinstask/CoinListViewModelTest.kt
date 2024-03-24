package com.example.coinstask

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.coinstask.data.dto.CoinDto
import com.example.coinstask.data.repository.ListCoinRepository
import com.example.coinstask.ui.viewmodel.CoinListViewModel
import com.example.coinstask.ui.viewmodel.ListCoinState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CoinListViewModelTest {

    @Mock
    lateinit var listCoinRepository: ListCoinRepository

    private lateinit var coinListViewModel: CoinListViewModel

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        coinListViewModel = CoinListViewModel()
    }

    private val fakeCoinsList = listOf(
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

    @Test
    fun `return success load state`() = runTest {

        // Mock the repository to return an empty list of coins
        `when`(listCoinRepository.getCoins()).thenReturn(emptyList())

        // Initialize the ViewModel
        coinListViewModel = CoinListViewModel()

        // Trigger the loading of coins
        coinListViewModel.loadCoins()

        // Advance the test dispatcher until all coroutines are completed
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert that the state is Success
        assertEquals(ListCoinState.Success(emptyList()), coinListViewModel.listScreenState.value)

    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

}