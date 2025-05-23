package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "inspirations")
data class Inspiration(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "photo_url") val photoUrl: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false
)