package com.rsupport.network_contract.response

import com.google.gson.annotations.SerializedName

sealed class ApiResponse<out T> {
    data class Success<T>(
        @SerializedName("data") val data: T
    ) : ApiResponse<T>()

    data class Fail(
        @SerializedName("error") val error: Throwable
    ) : ApiResponse<Nothing>()
}
