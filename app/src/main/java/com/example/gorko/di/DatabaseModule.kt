package com.example.gorko.di

import android.content.Context
import androidx.room.Room
import com.example.gorko.data.local.dao.ExpenseDao
import com.example.gorko.data.local.dao.TaskDao
import com.example.gorko.data.local.database.GorckoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GorckoDatabase =
        Room.databaseBuilder(appContext, GorckoDatabase::class.java, "gorko-db")
            .fallbackToDestructiveMigration() // Можно убрать, если не нужно
            .build()

    @Provides
    fun provideExpenseDao(db: GorckoDatabase): ExpenseDao = db.expenseDao()

    @Provides
    fun provideTaskDao(db: GorckoDatabase): TaskDao = db.taskDao()
}