package com.example.gorko.data.repository

import com.example.gorko.data.network.PexelsApi
import javax.inject.Inject

class PexelsRepository @Inject constructor(private val api: PexelsApi) {
    suspend fun getPhotos(query: String, page: Int): List<String> {
        val response = api.searchPhotos(query = query, page = page)
        return response.photos.map { it.src.medium }
    }
}