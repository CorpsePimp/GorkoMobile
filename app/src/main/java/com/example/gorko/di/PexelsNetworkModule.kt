package com.example.gorko.di

import com.example.gorko.data.network.PexelsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PexelsNetworkModule {

    @Provides
    @Singleton
    fun providePexelsApi(): PexelsApi = Retrofit.Builder()
        .baseUrl("https://api.pexels.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PexelsApi::class.java)
}