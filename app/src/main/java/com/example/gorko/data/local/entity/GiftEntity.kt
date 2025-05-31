package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(
    tableName = "gifts",
    foreignKeys = [
        ForeignKey(
            entity = WeddingEntity::class,
            parentColumns = ["id"],
            childColumns = ["weddingId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["weddingId"])]
)
data class GiftEntity(
    @PrimaryKey
    val id: String,
    val weddingId: String,
    val name: String,
    val description: String? = null,
    val price: Double,
    val url: String? = null, // Ссылка на товар
    val imageUrl: String? = null,
    val isPurchased: Boolean = false,
    val purchasedBy: String? = null, // Имя гостя, который купил подарок
    val createdAt: Long,
    val updatedAt: Long
)