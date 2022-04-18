package com.hopeitservice.cartcheckout.cart.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hopeitservice.cartcheckout.R
import com.hopeitservice.cartcheckout.cart.ViewModel.CartViewModel
import com.hopeitservice.cartcheckout.cart.helper.DeleteCartItem
import com.hopeitservice.cartcheckout.cart.localdatabase.CartModel

class CartListAdapter(
    private val activity: Activity,
    private val cartList: List<CartModel>,
    private val deleteCartItem: DeleteCartItem
):RecyclerView.Adapter<CartListAdapter.CartList>() {

    class CartList(item: View): RecyclerView.ViewHolder(item){

        val tv_pro_title: TextView = item.findViewById(R.id.tv_product_title)
        val tv_pro_price: TextView = item.findViewById(R.id.tv_product_price)
        val tv_remove: TextView = item.findViewById(R.id.tv_remove)
        val iv_product_image: ImageView = item.findViewById(R.id.iv_product_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartList {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cart_list, parent, false)

        return CartList(view)
    }

    override fun onBindViewHolder(holder: CartList, position: Int) {
        val cartModel = cartList.get(position)

        holder.tv_pro_title.text = cartModel.product_name
        holder.tv_pro_price.text = "\u20B9 "+cartModel.product_price

        Glide.with(activity)
            .load(cartModel.product_url)
            .thumbnail(
                Glide.with(activity).load(R.drawable.spinner_icon)
                    .apply(RequestOptions().override(100, 100))
            )
            .into(holder.iv_product_image)

        holder.tv_remove.setOnClickListener {
            deleteCartItem.removeCartItem(cartModel)
        }

    }

    override fun getItemCount(): Int {
        return cartList.size
    }
}