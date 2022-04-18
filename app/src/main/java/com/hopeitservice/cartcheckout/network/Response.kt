package com.hopeitservice.cartcheckout.network

import okhttp3.ResponseBody

sealed class Response<out T>{
    data class Success<out T>(val value: T) : Response<T>()

    data class Failure(
        val isNetworkError: Boolean,
        val code: Int,
        val error: ResponseBody?
    ): Response<Nothing>()
}