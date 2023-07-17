package com.example.coinstask.data.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName ("description") val description: String,
    @SerializedName ("id") val id: String,
    @SerializedName ("is_active") val is_active: Boolean,
    @SerializedName ("name") val name: String,
//    val tags: List<Tag>,
    @SerializedName ("type") val type: String
)