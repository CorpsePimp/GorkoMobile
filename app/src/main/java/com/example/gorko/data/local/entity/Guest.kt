package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "guests")
data class Guest(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "wedding_id") val weddingId: UUID,
    @ColumnInfo(name = "full_name") val fullName: String,
    @ColumnInfo(name = "birth_date") val birthDate: Long?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "status") val status: String // Invited, Confirmed, Declined
)