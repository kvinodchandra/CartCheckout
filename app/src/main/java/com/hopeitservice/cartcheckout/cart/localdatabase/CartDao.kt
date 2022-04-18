package com.hopeitservice.cartcheckout.cart.localdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CartDao {
    @Insert
    fun insertCartData(cartModel: CartModel)

    @Delete
    fun removeCartData(cartModel: CartModel)

    @Query("SELECT * FROM cart_data")
    fun getCartData(): LiveData<List<CartModel>>

}