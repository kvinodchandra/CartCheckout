package com.hopeitservice.cartcheckout.cart.helper

import android.app.Application
import com.hopeitservice.cartcheckout.cart.localdatabase.DatabaseHelper
import com.hopeitservice.cartcheckout.cart.repository.CartRepository

class CartApplication: Application() {

    val databaseHelper by lazy { DatabaseHelper.getDatabase(this) }

    val repository by lazy { CartRepository(databaseHelper.cartDao()) }
}