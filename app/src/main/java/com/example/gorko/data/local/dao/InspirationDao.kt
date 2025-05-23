package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.InspirationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InspirationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInspiration(inspiration: InspirationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInspirations(inspirations: List<InspirationEntity>)

    @Update
    suspend fun updateInspiration(inspiration: InspirationEntity)

    @Delete
    suspend fun deleteInspiration(inspiration: InspirationEntity)

    @Query("SELECT * FROM inspirations WHERE id = :inspirationId")
    suspend fun getInspirationById(inspirationId: String): InspirationEntity?

    @Query("SELECT * FROM inspirations ORDER BY createdAt DESC")
    fun getAllInspirations(): Flow<List<InspirationEntity>>

    @Query("SELECT * FROM inspirations WHERE category = :category ORDER BY createdAt DESC")
    fun getInspirationsByCategory(category: String): Flow<List<InspirationEntity>>

    @Query("SELECT * FROM inspirations WHERE isFavorite = 1 ORDER BY createdAt DESC")
    fun getFavoriteInspirations(): Flow<List<InspirationEntity>>

    @Query("UPDATE inspirations SET isFavorite = :isFavorite WHERE id = :inspirationId")
    suspend fun updateFavoriteStatus(inspirationId: String, isFavorite: Boolean)

    @Query("""
        SELECT * FROM inspirations 
        WHERE title LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%'
        ORDER BY createdAt DESC
    """)
    fun searchInspirations(query: String): Flow<List<InspirationEntity>>

    @Query("SELECT DISTINCT category FROM inspirations WHERE category IS NOT NULL ORDER BY category ASC")
    fun getAllCategories(): Flow<List<String>>

    @Query("DELETE FROM inspirations WHERE createdAt < :timestamp")
    suspend fun deleteOldInspirations(timestamp: Long)

    @Query("DELETE FROM inspirations")
    suspend fun clearInspirations()
}