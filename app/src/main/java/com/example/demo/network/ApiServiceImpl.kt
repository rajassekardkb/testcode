package com.example.demo.network


import com.example.demo.models.CardResponse
import com.example.demo.models.Product
import com.example.demo.models.ProfileResponse
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {

    suspend fun getExpenseList(data: String): CardResponse = apiService.getExpenseLists(data)
    suspend fun getEmployeeList(data: String): List<ProfileResponse> = apiService.getEmployeeList(data)
}