package com.example.demo.database

import androidx.room.*
import com.example.demo.models.Address
import com.example.demo.models.Companies
import com.example.demo.models.Profile
import kotlinx.coroutines.flow.Flow

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProfile(user: Profile)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(user: Address)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompany(user: Companies)

    @Query("SELECT * FROM Profile ORDER BY id ASC")
    fun getList(): Flow<List<Profile>>

    @Delete
    fun delete(alert: Profile)


    @Query("SELECT * FROM Profile")
    fun getTotalPrice(): Flow<List<Profile>>

    @Query("SELECT * FROM Address where ida=:id")
    fun getAddress(id: String): Flow<Address>
    @Query("SELECT * FROM Companies where idc=:id")
    fun getCompany(id: String): Flow<Companies>
}