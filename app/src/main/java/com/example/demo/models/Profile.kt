package com.example.demo.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Profile")
data class Profile(
    @PrimaryKey()
    @NonNull
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("profile_image")
    @ColumnInfo(name = "profile_image", defaultValue = "https://randomuser.me/api/portraits/women/1.jp")
    val profile_image: String?,
    @SerializedName("phone")
    @ColumnInfo(name = "phone", defaultValue = "00 00 00 00")
    val phone: String?,
    @ColumnInfo(name = "website", defaultValue = "www.gooogele.com")
    @SerializedName("website")
    val website: String?
)
