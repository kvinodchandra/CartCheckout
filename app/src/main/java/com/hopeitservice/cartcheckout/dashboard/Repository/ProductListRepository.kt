package com.hopeitservice.cartcheckout.dashboard.Repository

import com.hopeitservice.cartcheckout.base.BaseRepository
import com.hopeitservice.cartcheckout.network.RetrofitAPI

class ProductListRepository(
    private val api: RetrofitAPI
): BaseRepository() {

    suspend fun productList() = baseApiCall{
        api.getProductList()
    }
}