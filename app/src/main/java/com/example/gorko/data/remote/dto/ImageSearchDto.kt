package com.example.gorko.data.remote.dto

data class ImageSearchDto(
    val images: List<ImageDto>
)

data class ImageDto(
    val url: String,
    val thumbnailUrl: String,
    val photographer: String
)