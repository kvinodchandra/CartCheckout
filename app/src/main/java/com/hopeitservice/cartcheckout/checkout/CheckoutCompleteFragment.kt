package com.hopeitservice.cartcheckout.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.hopeitservice.cartcheckout.R
import com.hopeitservice.cartcheckout.cart.CartFragmentDirections

class CheckoutCompleteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_checkout_complete, container, false)

        view.findViewById<Button>(R.id.btn_ctn_shopping).setOnClickListener {
            val direction = CheckoutCompleteFragmentDirections.actionCheckoutToDashboard()

            findNavController().navigate(direction)
        }

        // Inflate the layout for this fragment
        return view
    }
}