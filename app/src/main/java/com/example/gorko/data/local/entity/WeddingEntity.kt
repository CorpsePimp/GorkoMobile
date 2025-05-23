package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "weddings")
data class WeddingEntity(
    @PrimaryKey
    val id: String,
    val groomName: String,
    val brideName: String,
    val weddingDate: LocalDate
)