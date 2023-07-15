package com.example.coinstask.data.api

import com.example.coinstask.data.dto.CoinDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.IOException
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

                    // Parse the JSON response into a list of CoinDto objects
                    val coinsJson = response.toString()
                    val jsonArray = JSONArray(coinsJson)
                    val coins = mutableListOf<CoinDto>()

                    for (i in 0 until jsonArray.length()) {
                        val coinObject = jsonArray.getJSONObject(i)
                        val id = coinObject.getString("id")
                        val name = coinObject.getString("name")
                        val is_active = coinObject.getBoolean("is_active")
                        val rank = coinObject.getInt("rank")
                        val symbol = coinObject.getString("symbol")
                        val is_new = coinObject.getBoolean("is_new")
                        val type = coinObject.getString("type")

                        val coinDto = CoinDto(
                            id = id,
                            name = name,
                            is_active = is_active,
                            rank = rank,
                            symbol = symbol,
                            is_new = is_new,
                            type = type
                        )
                        coins.add(coinDto)
                    }
                    coins
                } else {
                    // Handle the case when the response is not HTTP_OK
                    throw IOException("API request failed with response code $responseCode")
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