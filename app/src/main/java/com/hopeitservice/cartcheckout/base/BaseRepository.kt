package com.hopeitservice.cartcheckout.base

import com.hopeitservice.cartcheckout.network.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

abstract class BaseRepository {

    suspend fun <T> baseApiCall(
        apiCall: suspend () -> T
    ): Response<T>{
        return withContext(Dispatchers.IO){
            try {
                Response.Success(apiCall.invoke())
            }catch (throwable: Throwable){
                when(throwable){
                    is HttpException -> {
                        Response.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        Response.Failure(false, 0, null)
                    }
                }
            }
        }
    }
}