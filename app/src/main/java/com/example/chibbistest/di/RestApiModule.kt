package com.example.chibbistest.di

import com.example.chibbistest.data.RestApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestApiModule {
    @Provides
    fun getRestApi(): RestApi {
        return Retrofit.Builder()
            .baseUrl("https://front-task.chibbistest.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestApi::class.java)
    }
}