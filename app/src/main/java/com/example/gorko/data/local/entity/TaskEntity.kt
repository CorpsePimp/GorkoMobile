package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "tasks",
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
data class TaskEntity(
    @PrimaryKey
    val id: String,
    val weddingId: String,
    val title: String,
    val description: String? = null,
    val dueDateTime: Long,
    val isCompleted: Boolean = false,
    val createdAt: Long,
    val updatedAt: Long
)