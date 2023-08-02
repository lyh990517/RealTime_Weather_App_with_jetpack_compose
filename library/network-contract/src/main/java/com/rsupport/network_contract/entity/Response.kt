package com.rsupport.network_contract.entity

import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("header") val header: Header,
    @SerializedName("body") val body: Body
)