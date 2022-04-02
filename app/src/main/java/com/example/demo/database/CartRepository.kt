package com.example.demo.database


import androidx.annotation.WorkerThread
import com.example.demo.models.Address
import com.example.demo.models.Companies
import com.example.demo.models.Profile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CartRepository @Inject constructor(private val alertDao: CartDao) {
    val getList: Flow<List<Profile>> = alertDao.getList()

    val getTotalPrice: Flow<List<Profile>> = alertDao.getTotalPrice()
    fun getAddress(id: String): Flow<Address> = alertDao.getAddress(id)
    fun getCompany(id: String): Flow<Companies> = alertDao.getCompany(id)

    @WorkerThread
    suspend fun insertProfile(data: Profile) = withContext(Dispatchers.IO) {
        println("I am BAck-->" + data.id)
        println("I am BAck-->" + data.name)
        alertDao.insertProfile(data)
    }

    @WorkerThread
    suspend fun insertAddress(data: Address) = withContext(Dispatchers.IO) {
        println("I am BAck-->" + data.ida)
        println("I am BAck-->" + data.street)
        alertDao.insertAddress(data)
    }

    @WorkerThread
    suspend fun insertCompany(data: Companies) = withContext(Dispatchers.IO) {
        println("I am BAck-->" + data.idc)
        println("I am BAck-->" + data.name)
        alertDao.insertCompany(data)
    }
/*

    @WorkerThread
    suspend fun delete(alert: Product) = withContext(Dispatchers.IO) {
        alertDao.delete(alert)
    }
*/

    /*   @WorkerThread
       suspend fun getData(alert: Product) = withContext(Dispatchers.IO) {
           //alertDao.insert(alert)
       }

       @WorkerThread
       suspend fun update(qty: String, price: String, id: String) = withContext(Dispatchers.IO) {
           alertDao.update(qty, price, id)
       }

       @WorkerThread
       suspend fun getTotalPrice() = withContext(Dispatchers.IO) {
           alertDao.getTotalPrice()
       }*/
}