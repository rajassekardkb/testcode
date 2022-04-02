package com.example.demo.repository

import com.example.demo.models.CardResponse
import com.example.demo.models.Product
import com.example.demo.models.ProfileResponse
import com.example.demo.network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository
@Inject
constructor(private val apiServiceImpl: ApiServiceImpl) {
    fun getExpenseApiData(data: String): Flow<CardResponse> = flow {
        emit(apiServiceImpl.getExpenseList(data))
    }.flowOn(Dispatchers.IO)


    fun getEmployeeList(data: String): Flow<List<ProfileResponse>> = flow {
        emit(apiServiceImpl.getEmployeeList(data))
    }.flowOn(Dispatchers.IO)
}

