package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "vendors")
data class Vendor(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "city") val city: String,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "contact") val contact: String?
)