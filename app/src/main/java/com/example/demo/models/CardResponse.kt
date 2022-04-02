package com.example.demo.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CardResponse(
    @SerializedName("products")
    val data: List<Product>
)

@Entity(tableName = "Product")
data class Product(
    @SerializedName("name")
    val name: String,
    @PrimaryKey()
    @NonNull
    @SerializedName("id")
    val id: String,
    @SerializedName("product_id")
    val productID: String,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("zoom_thumb")
    val zoomThumb: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("href")
    val href: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("price")
    var price: String,
    @ColumnInfo(name = "tprice", defaultValue = "")
    val tprice: String,
    @SerializedName("special")
    val special: String
)