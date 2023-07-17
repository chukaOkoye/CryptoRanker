package com.example.coinstask.data.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName ("description") val description: String,
    @SerializedName ("id") val id: String,
    @SerializedName ("is_active") val is_active: Boolean
//    val is_new: Boolean,
//    val links: Links,
//    val logo: String,
//    val message: String,
//    val name: String,
//    val rank: Int,
//    val started_at: String,
//    val symbol: String,
//    val tags: List<Tag>,
//    val type: String,
//    val whitepaper: Whitepaper
)