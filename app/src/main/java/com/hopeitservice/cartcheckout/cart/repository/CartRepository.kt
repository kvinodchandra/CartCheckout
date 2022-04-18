package com.hopeitservice.cartcheckout.cart.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.hopeitservice.cartcheckout.base.BaseRepository
import com.hopeitservice.cartcheckout.cart.localdatabase.CartDao
import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel
import com.hopeitservice.cartcheckout.dashboard.model.Product

class CartRepository(private val cartDao: CartDao): BaseRepository() {

    val allCartData: LiveData<List<CartModel>> = cartDao.getCartData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addToCart(cartModel: CartModel){
        cartDao.insertCartData(cartModel)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun removeCartItem(cartModel: CartModel){
        cartDao.removeCartData(cartModel)
    }
}