package com.example.coinstask.data.dto

import com.example.coinstask.domain.CoinData
import com.google.gson.annotations.SerializedName

data class CoinDto(
    @SerializedName("id") val id: String,
    @SerializedName("is_active") val is_active: Boolean,
    @SerializedName("is_new") val is_new: Boolean,
    @SerializedName("name") val name: String,
    @SerializedName("rank") val rank: Int,
    @SerializedName("symbol") val symbol: String,
    @SerializedName("type") val type: String
)

fun CoinDto.toCoinData(): CoinData {
    return CoinData(
        id = id,
        name = name,
        is_active = is_active,
        rank = rank,
        symbol = symbol,
        is_new = is_new,
        type = type
        )
}