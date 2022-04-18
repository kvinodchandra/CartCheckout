package com.hopeitservice.cartcheckout.cart.localdatabase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_data")
class CartModel(
    @PrimaryKey(autoGenerate = true) var id: Int,
    @ColumnInfo(name="product_name") var product_name: String,
    @ColumnInfo(name="product_price") var product_price: String,
    @ColumnInfo(name="product_url") var product_url: String
)

