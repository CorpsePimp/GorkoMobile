package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(
    tableName = "expenses",
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
data class ExpenseEntity(
    @PrimaryKey
    val id: String,
    val weddingId: String,
    val title: String,
    val description: String? = null,
    val category: ExpenseCategory,
    val amount: BigDecimal,
    val isPaid: Boolean = false,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)