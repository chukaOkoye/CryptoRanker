package com.example.coinstask.data.dto

import com.google.gson.annotations.SerializedName

data class CoinDetailDto(
    @SerializedName ("description") val description: String,
    @SerializedName ("id") val id: String,
    @SerializedName ("is_active") val is_active: Boolean,
    @SerializedName ("name") val name: String,
    @SerializedName ("type") val type: String,
    @SerializedName("logo") val logo: String
)