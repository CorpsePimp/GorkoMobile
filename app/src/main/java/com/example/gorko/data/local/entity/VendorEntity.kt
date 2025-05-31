package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "vendors")
data class VendorEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val category: VendorCategory,
    val description: String,
    val city: String,
    val phone: String? = null,
    val email: String? = null,
    val website: String? = null,
    val telegram: String? = null,
    val imageUrls: String,
    val rating: Float = 0f, // от 0 до 5
    val reviewsCount: Int = 0,
    val priceRange: String? = null, // Например: "₽₽₽" или "50000-100000"
    val isFavorite: Boolean = false,
    val createdAt: Long,
    val updatedAt: Long
)