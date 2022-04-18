package com.hopeitservice.cartcheckout.network

import com.hopeitservice.cartcheckout.dashboard.model.ProductListModel
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("nancymadan/assignment/db")
    suspend fun getProductList():ProductListModel
}