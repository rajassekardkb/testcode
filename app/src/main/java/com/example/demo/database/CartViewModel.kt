package com.example.demo.database

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.demo.models.Address
import com.example.demo.models.Companies
import com.example.demo.models.Product
import com.example.demo.models.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlertViewModel @Inject constructor(private val userRepository: CartRepository) :
    ViewModel() {

    val getList: LiveData<List<Profile>>
        get() =
            userRepository.getList.flowOn(Dispatchers.Main)
                .asLiveData(context = viewModelScope.coroutineContext)
    val details: LiveData<List<Profile>>
        get() =
            userRepository.getTotalPrice.flowOn(Dispatchers.Main)
                .asLiveData(context = viewModelScope.coroutineContext)

    fun getAddress(id:String): LiveData<Address> {
        return userRepository.getAddress(id).flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)
    }
    fun getCompany(id:String): LiveData<Companies> {
        return userRepository.getCompany(id).flowOn(Dispatchers.Main)
            .asLiveData(context = viewModelScope.coroutineContext)
    }


    fun insert(data: Profile) {
        viewModelScope.launch {
            userRepository.insertProfile(data)
        }
    }

    fun insertAddress(data: Address) {
        viewModelScope.launch {
            userRepository.insertAddress(data)
        }
    }

    fun insertCompany(data: Companies) {
        viewModelScope.launch {
            userRepository.insertCompany(data)
        }
    }

    fun delete(user: Product) {
        viewModelScope.launch {
            //  userRepository.delete(user)
        }
    }

    fun update(qty: String, price: String, id: String) {
        viewModelScope.launch {
            //  userRepository.update(qty, price, id)
        }
    }

    fun getTotal(user: Product) {
        viewModelScope.launch {
            // userRepository.getTotalPrice()
        }
    }

}