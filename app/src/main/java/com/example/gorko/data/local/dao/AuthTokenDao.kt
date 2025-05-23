package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.AuthTokenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthTokenDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: AuthTokenEntity)
    
    @Query("SELECT * FROM auth_tokens WHERE userId = :userId")
    suspend fun getTokenByUserId(userId: String): AuthTokenEntity?
    
    @Query("SELECT * FROM auth_tokens LIMIT 1")
    fun getCurrentToken(): Flow<AuthTokenEntity?>
    
    @Query("DELETE FROM auth_tokens WHERE userId = :userId")
    suspend fun deleteToken(userId: String)
    
    @Query("DELETE FROM auth_tokens")
    suspend fun clearAllTokens()
}