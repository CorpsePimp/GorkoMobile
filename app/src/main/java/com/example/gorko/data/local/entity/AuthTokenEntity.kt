package com.example.gorko.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "auth_tokens")
data class AuthTokenEntity(
    @PrimaryKey
    val userId: String,
    val accessToken: String,
    val refreshToken: String,
    val expiresAt: Long,
    val createdAt: Long
)