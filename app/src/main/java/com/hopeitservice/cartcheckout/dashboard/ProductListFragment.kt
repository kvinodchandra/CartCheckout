package com.hopeitservice.cartcheckout.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hopeitservice.cartcheckout.R
import com.hopeitservice.cartcheckout.base.BaseFragment
import com.hopeitservice.cartcheckout.cart.helper.CartApplication
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModel
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModelFactory
import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel
import com.hopeitservice.cartcheckout.dashboard.Repository.ProductListRepository
import com.hopeitservice.cartcheckout.dashboard.adpter.ProductListAdapter
import com.hopeitservice.cartcheckout.dashboard.helper.AddToCart
import com.hopeitservice.cartcheckout.dashboard.model.Product
import com.hopeitservice.cartcheckout.dashboard.model.ProductListModel
import com.hopeitservice.cartcheckout.dashboard.viewmodel.ProductListViewModel
import com.hopeitservice.cartcheckout.databinding.FragmentProductListBinding
import com.hopeitservice.cartcheckout.network.Response
import com.hopeitservice.cartcheckout.network.RetrofitAPI


class ProductListFragment : BaseFragment<ProductListViewModel, FragmentProductListBinding, ProductListRepository>(),
    AddToCart {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getProductListVM()

        viewModel.productListResponse.observe(this, Observer {
            binding.progressIndicator.visibility = View.GONE
            when(it){
                is Response.Success -> {
                    Log.e("ResponseSuccess", it.toString())
                    setItemInListView(it.value)
                }

                is Response.Failure -> {
                    Log.e("ResponseFails", it.toString())
                }
            }
        })

        binding.ivGotoCart.setOnClickListener {
            findNavController().navigate(R.id.cartFragment)
        }

        cartViewModel.allCartListVM.observe( this) { cart ->
            // Update the cached copy of the words in the adapter.
            cart.let {
               if (it.size>0){
                   binding.cartCount.visibility = View.VISIBLE
               }
            }
        }
    }

    private val cartViewModel: CartViewModel by viewModels {
        CartViewModelFactory((requireActivity().applicationContext as CartApplication).repository)
    }

    private fun setItemInListView(value: ProductListModel) {
        val adapter = ProductListAdapter(value.products, requireActivity(), this)

        val layoutManager = LinearLayoutManager(activity)
        binding.rcvProductList.layoutManager = layoutManager
        binding.rcvProductList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun getViewModel() = ProductListViewModel::class.java

    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentProductListBinding.inflate(inflater, container, false)

    override fun getFragmentRepository() = ProductListRepository(retrofitService.buildAPI(RetrofitAPI::class.java))

    override fun AddToCart(product: Product) {
        val shake: Animation
        shake = AnimationUtils.loadAnimation(
            requireActivity(),
            R.anim.shake
        )

        binding.ivGotoCart.startAnimation(shake) // starts animation

        cartViewModel.addToCartVM(CartModel(0, product.name, product.price, product.image_url))
        binding.cartCount.visibility = View.VISIBLE
    }


}