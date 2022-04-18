package com.hopeitservice.cartcheckout.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.hopeitservice.cartcheckout.R
import com.hopeitservice.cartcheckout.cart.localdatabase.CartDao
import com.hopeitservice.cartcheckout.network.RetrofitService

abstract class BaseFragment<V: ViewModel, B: ViewBinding, R: BaseRepository>: Fragment() {

    lateinit var binding: B
    lateinit var viewModel: V

    val retrofitService = RetrofitService()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getFragmentBinding(inflater, container)
        val factory = ViewModelFactory(getFragmentRepository())
        viewModel = ViewModelProvider(this, factory).get(getViewModel())
        return binding.root
    }

    abstract fun getViewModel(): Class<V>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    abstract fun getFragmentRepository(): R
}