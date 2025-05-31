package com.example.gorko.domain.model

data class ImageSearchResult(
    val images: List<Image>
)

data class Image(
    val url: String,
    val thumbnailUrl: String,
    val photographer: String
)