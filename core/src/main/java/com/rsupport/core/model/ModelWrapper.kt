package com.rsupport.core.model

sealed class ModelWrapper<T> {
    data class Success<T>(val model: T) : ModelWrapper<T>()
    data class Fail<T>(val error: Throwable) : ModelWrapper<T>()
}