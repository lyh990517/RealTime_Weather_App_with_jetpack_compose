package com.rsupport.network_contract.entity

import com.google.gson.annotations.SerializedName

data class Body(
    @SerializedName("dataType") val dataType: String,
    @SerializedName("items") val items: Items,
    @SerializedName("pageNo") val pageNo: Int,
    @SerializedName("numOfRows") val numOfRows: Int,
    @SerializedName("totalCount") val totalCount: Int
)