package com.hopeitservice.cartcheckout.dashboard.adpter

import android.app.Activity
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
import com.hopeitservice.cartcheckout.dashboard.helper.AddToCart
import com.hopeitservice.cartcheckout.dashboard.model.Product


class ProductListAdapter(
    private val productList: ArrayList<Product>,
    private val activity: Activity,
    private  val addToCart: AddToCart
): RecyclerView.Adapter<ProductListAdapter.ProductList>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductList {
        val view =LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product_list, parent, false)

        return ProductList(view)
    }

    override fun onBindViewHolder(holder: ProductList, position: Int) {
        val pro_model = productList.get(position)

        holder.tv_pro_title.text = pro_model.name
        holder.tv_pro_price.text = "\u20B9 "+pro_model.price
        holder.rb_rating.rating = pro_model.rating.toFloat()

        Glide.with(activity)
            .load(pro_model.image_url)
            .thumbnail(
                Glide.with(activity).load(R.drawable.spinner_icon)
                    .apply(RequestOptions().override(100, 100))
            )
            .into(holder.iv_product_image)

        holder.tv_add_to_cart.setOnClickListener {
            addToCart.AddToCart(pro_model)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductList(item: View): RecyclerView.ViewHolder(item) {
        val tv_pro_title: TextView = item.findViewById(R.id.tv_product_title)
        val tv_pro_price: TextView = item.findViewById(R.id.tv_product_price)
        val iv_product_image: ImageView = item.findViewById(R.id.iv_product_image)
        val rb_rating: RatingBar = item.findViewById(R.id.rb_product_rating)
        val tv_add_to_cart: TextView = item.findViewById(R.id.tv_add_to_cart)
    }
}
