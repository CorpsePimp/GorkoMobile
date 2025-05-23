package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.SyncEntity
import com.example.gorko.data.local.entity.SyncOperation
import kotlinx.coroutines.flow.Flow

@Dao
interface SyncDao {
    @Insert
    suspend fun insertSyncEntity(sync: SyncEntity): Long
    
    @Update
    suspend fun updateSyncEntity(sync: SyncEntity)
    
    @Delete
    suspend fun deleteSyncEntity(sync: SyncEntity)
    
    @Query("SELECT * FROM sync_pending ORDER BY createdAt ASC")
    fun getAllPendingSync(): Flow<List<SyncEntity>>
    
    @Query("SELECT * FROM sync_pending WHERE entityType = :entityType AND entityId = :entityId")
    suspend fun getSyncByEntity(entityType: String, entityId: String): SyncEntity?
    
    @Query("DELETE FROM sync_pending WHERE id = :id")
    suspend fun deleteSyncById(id: Long)
    
    @Query("UPDATE sync_pending SET attempts = attempts + 1, lastAttemptAt = :attemptTime WHERE id = :id")
    suspend fun incrementAttempts(id: Long, attemptTime: String)
    
    @Query("DELETE FROM sync_pending WHERE attempts > :maxAttempts")
    suspend fun deleteFailedSync(maxAttempts: Int = 5)
    
    @Query("DELETE FROM sync_pending")
    suspend fun clearAllSync()
}