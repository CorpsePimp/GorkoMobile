package com.example.gorko.data.network

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

data class PexelsPhoto(val id: Long, val src: Src)
data class Src(val medium: String, val large: String)

data class PexelsResponse(val photos: List<PexelsPhoto>, val next_page: String?)

interface PexelsApi {
    @Headers("Authorization: kgDsvV1IWBksLnDIooH2hiDInLo4hRbBXjlCI8DrzWWkzWnbBzMyreOp")
    @GET("v1/search")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("per_page") perPage: Int = 15,
        @Query("page") page: Int = 1
    ): PexelsResponse
}