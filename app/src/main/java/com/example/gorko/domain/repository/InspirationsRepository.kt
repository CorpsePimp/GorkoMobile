package com.example.gorko.domain.repository

import com.example.gorko.domain.model.ImageSearchResult
import com.example.gorko.domain.model.RandomImage

interface InspirationsRepository {
    suspend fun getRandomImage(): RandomImage
    suspend fun searchImages(query: String): ImageSearchResult
}