package com.example.coinstask.data.dto

import com.example.coinstask.domain.CoinData

data class CoinDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
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