package com.example.gorko.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey val id: UUID = UUID.randomUUID(),
    @ColumnInfo(name = "budget_id") val budgetId: UUID,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "amount") val amount: Double
)