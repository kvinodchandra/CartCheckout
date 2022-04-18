package com.hopeitservice.cartcheckout.cart.helper

import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel

interface DeleteCartItem {
    fun removeCartItem(cartModel: CartModel)
}