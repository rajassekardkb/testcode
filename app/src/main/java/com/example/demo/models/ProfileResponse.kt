package com.example.demo.models

import com.google.gson.annotations.SerializedName


data class ProfileResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profile_image")
    val image: String,
    @SerializedName("address")
    val address: address?,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("website")
    val website: String,
    @SerializedName("company")
    val company: Company?
)