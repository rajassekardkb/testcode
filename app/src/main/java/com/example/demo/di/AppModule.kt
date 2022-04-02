package com.example.demo.di

import android.content.Context
import androidx.room.Room
import com.example.demo.cons.Cons.BASE_URL
import com.example.demo.database.CartDao
import com.example.demo.database.CartDatabase
import com.example.demo.database.CartRepository
import com.example.demo.network.ApiService
import com.example.demo.network.BasicAuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providesAlertDao(userDatabase: CartDatabase): CartDao = userDatabase.userDao()

    @Provides
    @Singleton
    fun providesAlertDatabase(@ApplicationContext context: Context):CartDatabase
            = Room.databaseBuilder(context,CartDatabase::class.java,"ProfileDetails").allowMainThreadQueries().build()

    @Provides
    fun providesUserRepository(userDao: CartDao) : CartRepository
            = CartRepository(userDao)

    @Provides
    fun providesUrlTest() = BASE_URL

    @Singleton
    @Provides
    fun providesOkHttpClient(): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(BasicAuthInterceptor("PortOfOakland", "OaklandPortal@2022!*"))
            .build()

    @Provides
    @Singleton
    fun providesApiService(okHttpClient: OkHttpClient,url: String): ApiService =
        Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
}