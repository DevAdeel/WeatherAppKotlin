package com.kotlin.watherapp.core

sealed class RetroResponse<out T> {
    data class Success<out R>(val value: R) : RetroResponse<R>()

    data class Failure(
        val message: String,
        val throwable: Throwable?
    ) : RetroResponse<Nothing>()

    object Loading : RetroResponse<Nothing>()
}