package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "weddings")
data class Wedding(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "groom_name") val groomName: String,
    @ColumnInfo(name = "bride_name") val brideName: String,
    @ColumnInfo(name = "wedding_date") val weddingDate: Long
)