package com.example.coinstask.data.api

import com.example.coinstask.data.dto.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiService {
    private val baseUrl = "https://api.coinpaprika.com/v1"

    suspend fun getCoins(): HttpURLConnection? = withContext(Dispatchers.IO) {
        val url = URL("$baseUrl/coins")
        val connection = url.openConnection() as? HttpURLConnection

        return@withContext try {
            connection?.apply {
                requestMethod = "GET"
                connectTimeout = 10000
                readTimeout = 10000

                // Get the response from the API
                val responseCode = responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()
                    response.toString()
                } else {
                    null
                }
            }
        } finally {
            connection?.disconnect()
        }
    }

    suspend fun getCoinDetails(coinId: String): HttpURLConnection? = withContext(Dispatchers.IO) {
        val url = URL("$baseUrl/coins/$coinId")
        val connection = url.openConnection() as? HttpURLConnection

        return@withContext try {
            connection?.apply {
                requestMethod = "GET"
                connectTimeout = 10000
                readTimeout = 10000

                // Get the response from the API
                val responseCode = responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val reader = BufferedReader(InputStreamReader(inputStream))
                    val response = StringBuilder()
                    var line: String?
                    while (reader.readLine().also { line = it } != null) {
                        response.append(line)
                    }
                    reader.close()
                    response.toString()
                } else {
                    null
                }
            }
        } finally {
            connection?.disconnect()
        }
    }
}