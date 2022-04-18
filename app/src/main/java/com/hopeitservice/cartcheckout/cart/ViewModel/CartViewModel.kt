package com.hopeitservice.cartcheckout.cart.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel
import com.hopeitservice.cartcheckout.cart.repository.CartRepository
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository): ViewModel() {

    val allCartListVM: LiveData<List<CartModel>> =  cartRepository.allCartData

    fun addToCartVM(cartModel: CartModel) = viewModelScope.launch {
        cartRepository.addToCart(cartModel)
    }

    fun removeCartItemVM(cartModel: CartModel) = viewModelScope.launch {
        cartRepository.removeCartItem(cartModel)
    }
}