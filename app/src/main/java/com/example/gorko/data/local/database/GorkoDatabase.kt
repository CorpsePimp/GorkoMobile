package com.example.gorko.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.example.gorko.data.local.converters.Converters
import com.example.gorko.data.local.dao.*
import com.example.gorko.data.local.entity.*
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory

@Database(
    entities = [
        UserEntity::class,
        WeddingEntity::class,
        UserWeddingCrossRef::class,
        TaskEntity::class,
        ExpenseEntity::class,
        GuestEntity::class,
        VendorEntity::class,
        GiftEntity::class,
        InspirationEntity::class,
        SyncEntity::class,
        AuthTokenEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class GorkoDatabase : RoomDatabase() {
    
    abstract fun userDao(): UserDao
    abstract fun weddingDao(): WeddingDao
    abstract fun taskDao(): TaskDao
    abstract fun expenseDao(): ExpenseDao
    abstract fun guestDao(): GuestDao
    abstract fun vendorDao(): VendorDao
    abstract fun giftDao(): GiftDao
    abstract fun inspirationDao(): InspirationDao
    abstract fun syncDao(): SyncDao
    abstract fun authTokenDao(): AuthTokenDao

    companion object {
        @Volatile
        private var INSTANCE: GorkoDatabase? = null
        
        private const val DATABASE_NAME = "gorko_database.db"
        private const val DATABASE_PASSPHRASE = "GorkoSecureDB2025!" // В продакшене использовать более сложный ключ
        
        fun getInstance(context: Context): GorkoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = buildDatabase(context)
                INSTANCE = instance
                instance
            }
        }
        
        private fun buildDatabase(context: Context): GorkoDatabase {
            // Создаем фабрику для SQLCipher
            val passphrase = SQLiteDatabase.getBytes(DATABASE_PASSPHRASE.toCharArray())
            val factory = SupportFactory(passphrase)
            
            return Room.databaseBuilder(
                context.applicationContext,
                GorkoDatabase::class.java,
                DATABASE_NAME
            )
                .openHelperFactory(factory) // Используем SQLCipher для шифрования
                .fallbackToDestructiveMigration() // В продакшене лучше использовать миграции
                .build()
        }
        
        // Метод для тестирования без шифрования
        fun getTestInstance(context: Context): GorkoDatabase {
            return Room.inMemoryDatabaseBuilder(
                context.applicationContext,
                GorkoDatabase::class.java
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}