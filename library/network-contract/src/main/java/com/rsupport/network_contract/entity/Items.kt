package com.rsupport.network_contract.entity

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName("item") val item: List<WeatherItem>
)