package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "gifts")
data class Gift(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "wedding_id") val weddingId: UUID,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "url") val url: String?,
    @ColumnInfo(name = "photo_path") val photoPath: String?
)