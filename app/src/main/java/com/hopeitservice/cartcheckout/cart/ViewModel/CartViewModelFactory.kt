package com.hopeitservice.cartcheckout.cart.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hopeitservice.cartcheckout.cart.repository.CartRepository
import java.lang.IllegalArgumentException

class CartViewModelFactory(private val cartRepository: CartRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CartViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return CartViewModel(cartRepository) as T
        }

        throw IllegalArgumentException("")
    }
}