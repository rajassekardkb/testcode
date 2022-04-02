package com.example.demo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demo.models.Address
import com.example.demo.models.Companies
import com.example.demo.models.Profile

@Database(entities = [Profile::class, Address::class,Companies::class], version = 1, exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun userDao(): CartDao

}