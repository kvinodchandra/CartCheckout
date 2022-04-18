package com.hopeitservice.cartcheckout.navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.hopeitservice.cartcheckout.R
import com.hopeitservice.cartcheckout.cart.helper.CartApplication
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModel
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModelFactory

class NavigationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
     }

    private val cartViewModel: CartViewModel by viewModels {
        CartViewModelFactory((application as CartApplication).repository)
    }
}