package com.example.demo.network

import com.example.demo.models.CardResponse
import com.example.demo.models.ProfileResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("v2/5def7b172f000063008e0aa2")
    @FormUrlEncoded
    suspend fun getExpenseLists(
        @Field("countryCode") countryCode: String = "hdgfh"
    ): CardResponse

    @POST("v2/5d565297300000680030a986")
    @FormUrlEncoded
    suspend fun getEmployeeList(
        @Field("demo") countryCode: String?
    ): List<ProfileResponse>

}