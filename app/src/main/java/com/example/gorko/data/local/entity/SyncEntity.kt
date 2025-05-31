package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "sync_pending")
data class SyncEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val entityType: String, // task, expense, guest, gift
    val entityId: String,
    val operation: SyncOperation,
    val data: String, // JSON данные для синхронизации
    val createdAt: Long,
    val attempts: Int = 0,
    val lastAttemptAt: Long
)

enum class SyncOperation {
    CREATE,
    UPDATE,
    DELETE
}