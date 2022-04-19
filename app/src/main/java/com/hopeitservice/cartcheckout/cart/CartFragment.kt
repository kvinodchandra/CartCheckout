package com.hopeitservice.cartcheckout.cart

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hopeitservice.cartcheckout.R
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModel
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModelFactory
import com.hopeitservice.cartcheckout.cart.adapter.CartListAdapter
import com.hopeitservice.cartcheckout.cart.helper.CartApplication
import com.hopeitservice.cartcheckout.cart.helper.DeleteCartItem
import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel
import com.hopeitservice.cartcheckout.databinding.FragmentCartBinding
import java.util.*
import kotlin.concurrent.timerTask

class CartFragment : Fragment(), DeleteCartItem {

    var amount: Float = 0F
    private lateinit var _binding: FragmentCartBinding
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private fun setCartList(cartList: List<CartModel>) {
        val adapter = CartListAdapter(requireActivity(), cartList, this)

        var lm = LinearLayoutManager(activity)
        binding.rcvCartList.layoutManager = lm
        binding.rcvCartList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private val cartViewModel: CartViewModel by viewModels {
        CartViewModelFactory((requireActivity().applicationContext as CartApplication).repository)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        val view = binding.root
        cartViewModel.allCartListVM.observe( this) { cart ->
            // Update the cached copy of the words in the adapter.
            cart.let {
                Log.e("CartData", it.toString())
                amount=0F
                setCartList(it)
                totalAmount(it)
            }
        }

        binding.btnCheckout.setOnClickListener{
            Handler().postDelayed({
                binding.progressIndicator.visibility = View.GONE

                val direction = CartFragmentDirections.actionCartToCheckout()

                findNavController().navigate(direction)
            }, 10000)
            binding.progressIndicator.visibility = View.VISIBLE

        }

        // Inflate the layout for this fragment
        return view
    }

    fun totalAmount(cartList: List<CartModel>) {

        for (ct in cartList){
            amount = amount + ct.product_price.toFloat()
        }

        binding.totalAmount.text = "Toral Amount: "+amount.toString()
    }

    override fun removeCartItem(cartModel: CartModel) {
        cartViewModel.removeCartItemVM(cartModel)
    }
}