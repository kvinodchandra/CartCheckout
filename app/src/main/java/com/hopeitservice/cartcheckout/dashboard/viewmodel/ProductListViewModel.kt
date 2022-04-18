package com.hopeitservice.cartcheckout.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopeitservice.cartcheckout.dashboard.Repository.ProductListRepository
import com.hopeitservice.cartcheckout.dashboard.model.ProductListModel
import com.hopeitservice.cartcheckout.network.Response
import kotlinx.coroutines.launch

class ProductListViewModel(
    private val repository: ProductListRepository
): ViewModel() {

    private val productlist: MutableLiveData<Response<ProductListModel>> = MutableLiveData()

    val productListResponse: LiveData<Response<ProductListModel>>
    get() = productlist

    fun getProductListVM()=viewModelScope.launch {
        productlist.value = repository.productList()
    }
}