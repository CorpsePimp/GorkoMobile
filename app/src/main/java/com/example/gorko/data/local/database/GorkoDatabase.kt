package com.example.gorko.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gorko.data.local.dao.ExpenseDao
import com.example.gorko.data.local.dao.TaskDao
import com.example.gorko.data.local.entity.ExpenseEntity
import com.example.gorko.data.local.entity.TaskEntity

@Database(
    entities = [ExpenseEntity::class, TaskEntity::class],
    version = 2
)
abstract class GorckoDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun taskDao(): TaskDao
}