package com.example.gorko.data.remote

import com.example.gorko.data.remote.api.InspirationsApi
import com.example.gorko.data.remote.dto.ImageSearchDto
import com.example.gorko.data.remote.dto.RandomImageDto

class RemoteDataSource(private val api: InspirationsApi) {
    suspend fun getRandomImage(): RandomImageDto {
        return api.getRandomImage()
    }

    suspend fun searchImages(query: String): ImageSearchDto {
        return api.searchImages(query)
    }
}