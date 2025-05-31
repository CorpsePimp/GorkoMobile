package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "inspirations")
data class InspirationEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String? = null,
    val imageUrl: String,
    val category: String? = null, // Декор, Платья, Торты, Букеты и т.д.
    val sourceUrl: String? = null, // Источник идеи
    val isFavorite: Boolean = false,
    val createdAt: Long
)