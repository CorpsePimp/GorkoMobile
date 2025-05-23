package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.WeddingEntity
import com.example.gorko.data.local.entity.UserWeddingCrossRef
import com.example.gorko.data.local.relations.WeddingWithUsers
import kotlinx.coroutines.flow.Flow

@Dao
interface WeddingDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWedding(wedding: WeddingEntity): Long

    @Update
    suspend fun updateWedding(wedding: WeddingEntity)

    @Delete
    suspend fun deleteWedding(wedding: WeddingEntity)

    @Query("SELECT * FROM weddings WHERE id = :weddingId")
    suspend fun getWeddingById(weddingId: String): WeddingEntity?

    @Query("SELECT * FROM weddings WHERE id = :weddingId")
    fun getWeddingFlow(weddingId: String): Flow<WeddingEntity?>

    @Transaction
    @Query("SELECT * FROM weddings WHERE id = :weddingId")
    suspend fun getWeddingWithUsers(weddingId: String): WeddingWithUsers?

    // Cross-reference operations
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserWeddingCrossRef(crossRef: UserWeddingCrossRef)

    @Delete
    suspend fun deleteUserWeddingCrossRef(crossRef: UserWeddingCrossRef)

    @Query("SELECT * FROM weddings WHERE id IN (SELECT weddingId FROM user_wedding WHERE userId = :userId)")
    fun getUserWeddings(userId: String): Flow<List<WeddingEntity>>

    @Query("DELETE FROM weddings")
    suspend fun clearWeddings()
}