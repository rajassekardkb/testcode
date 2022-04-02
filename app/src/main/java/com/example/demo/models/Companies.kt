package com.example.demo.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "Companies")
data class Companies(
    @PrimaryKey()
    @NonNull
    @SerializedName("idc")
    val idc: String,
    @SerializedName("name")
    @ColumnInfo(name = "name", defaultValue = " ")
    val name: String?,
    @SerializedName("catchPhrase")
    @ColumnInfo(name = "catchPhrase", defaultValue = " ")
    val catchPhrase: String?,
    @ColumnInfo(name = "bs", defaultValue = " ")
    @SerializedName("bs")
    val bs: String?,
)
