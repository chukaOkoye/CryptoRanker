package com.example.coinstask.data.api

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ApiService {
    fun getCoins(): Any? {
        val url = URL("https://api.coinpaprika.com/v1/coins")
        val connection = url.openConnection() as? HttpURLConnection

        return try ({
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
        }).toString() finally {
            connection?.disconnect()
        }
    }
}