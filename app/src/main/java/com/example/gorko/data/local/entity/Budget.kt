package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "budgets")
data class Budget(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "wedding_id") val weddingId: UUID,
    @ColumnInfo(name = "total_spent") val totalSpent: Double = 0.0
)