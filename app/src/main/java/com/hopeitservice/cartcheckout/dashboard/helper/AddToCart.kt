package com.hopeitservice.cartcheckout.dashboard.helper

import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel
import com.hopeitservice.cartcheckout.dashboard.model.Product

interface AddToCart {

    fun AddToCart(product: Product)
}