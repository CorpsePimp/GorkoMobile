package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(
    tableName = "guests",
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
data class GuestEntity(
    @PrimaryKey
    val id: String,
    val weddingId: String,
    val fullName: String,
    val phone: String? = null,
    val email: String? = null,
    val birthDate: Long,
    val status: GuestStatus = GuestStatus.INVITED,
    val tableNumber: Int? = null, // Номер стола для рассадки
    val notes: String? = null, // Дополнительные заметки
    val invitationSentAt: Long,
    val responseReceivedAt: Long,
    val createdAt: Long,
    val updatedAt: Long
)