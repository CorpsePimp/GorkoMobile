package com.example.gorko.data.remote.api

import com.example.gorko.data.remote.dto.ImageSearchDto
import com.example.gorko.data.remote.dto.RandomImageDto
import retrofit2.http.GET
import retrofit2.http.Query

interface InspirationsApi {
    @GET("api/random-image/")
    suspend fun getRandomImage(): RandomImageDto

    @GET("api/search-images/")
    suspend fun searchImages(
        @Query("query") query: String
    ): ImageSearchDto
}