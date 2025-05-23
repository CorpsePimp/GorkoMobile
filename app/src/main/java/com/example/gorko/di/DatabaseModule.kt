package com.example.gorko.di

import android.content.Context
import com.example.gorko.data.local.dao.*
import com.example.gorko.data.local.database.GorkoDatabase
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
    fun provideGorkoDatabase(
        @ApplicationContext context: Context
    ): GorkoDatabase {
        return GorkoDatabase.getInstance(context)
    }
    
    @Provides
    @Singleton
    fun provideUserDao(database: GorkoDatabase): UserDao {
        return database.userDao()
    }
    
    @Provides
    @Singleton
    fun provideWeddingDao(database: GorkoDatabase): WeddingDao {
        return database.weddingDao()
    }
    
    @Provides
    @Singleton
    fun provideTaskDao(database: GorkoDatabase): TaskDao {
        return database.taskDao()
    }
    
    @Provides
    @Singleton
    fun provideExpenseDao(database: GorkoDatabase): ExpenseDao {
        return database.expenseDao()
    }
    
    @Provides
    @Singleton
    fun provideGuestDao(database: GorkoDatabase): GuestDao {
        return database.guestDao()
    }
    
    @Provides
    @Singleton
    fun provideVendorDao(database: GorkoDatabase): VendorDao {
        return database.vendorDao()
    }
    
    @Provides
    @Singleton
    fun provideGiftDao(database: GorkoDatabase): GiftDao {
        return database.giftDao()
    }
    
    @Provides
    @Singleton
    fun provideInspirationDao(database: GorkoDatabase): InspirationDao {
        return database.inspirationDao()
    }
    
    @Provides
    @Singleton
    fun provideSyncDao(database: GorkoDatabase): SyncDao {
        return database.syncDao()
    }
    
    @Provides
    @Singleton
    fun provideAuthTokenDao(database: GorkoDatabase): AuthTokenDao {
        return database.authTokenDao()
    }
}