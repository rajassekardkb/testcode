package com.example.demo.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Address")
data class Address(
    @PrimaryKey()
    @NonNull
    @SerializedName("id")
    val ida: String,
    @SerializedName("street")
    val street: String?,
    @SerializedName("suite")
    val suite: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("zipcode")
    val zipcode: String?
)
