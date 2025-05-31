package com.example.gorko.data.local.database
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.gorko.data.local.dao.ExpenseDao
import com.example.gorko.data.local.entity.ExpenseEntity

@Database(entities = [ExpenseEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}