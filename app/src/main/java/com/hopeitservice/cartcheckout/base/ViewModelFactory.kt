package com.hopeitservice.cartcheckout.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModel
import com.hopeitservice.cartcheckout.cart.repository.CartRepository
import com.hopeitservice.cartcheckout.dashboard.Repository.ProductListRepository
import com.hopeitservice.cartcheckout.dashboard.viewmodel.ProductListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(ProductListViewModel::class.java) -> ProductListViewModel(repository as ProductListRepository) as T

//            modelClass.isAssignableFrom(CartViewModel::class.java) -> CartViewModel(repository as CartRepository) as T

            else -> throw IllegalArgumentException("ViewModelClass Not found")
        }
    }
}