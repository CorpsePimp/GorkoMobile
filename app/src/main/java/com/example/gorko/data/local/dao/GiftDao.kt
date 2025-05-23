package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.GiftEntity
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface GiftDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGift(gift: GiftEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGifts(gifts: List<GiftEntity>)

    @Update
    suspend fun updateGift(gift: GiftEntity)

    @Delete
    suspend fun deleteGift(gift: GiftEntity)

    @Query("DELETE FROM gifts WHERE id = :giftId")
    suspend fun deleteGiftById(giftId: String)

    @Query("SELECT * FROM gifts WHERE id = :giftId")
    suspend fun getGiftById(giftId: String): GiftEntity?

    @Query("SELECT * FROM gifts WHERE weddingId = :weddingId ORDER BY createdAt DESC")
    fun getWeddingGifts(weddingId: String): Flow<List<GiftEntity>>

    @Query("SELECT * FROM gifts WHERE weddingId = :weddingId AND isPurchased = 0 ORDER BY price DESC")
    fun getUnpurchasedGifts(weddingId: String): Flow<List<GiftEntity>>

    @Query("SELECT * FROM gifts WHERE weddingId = :weddingId AND isPurchased = 1 ORDER BY createdAt DESC")
    fun getPurchasedGifts(weddingId: String): Flow<List<GiftEntity>>

    @Query("""
        SELECT * FROM gifts 
        WHERE weddingId = :weddingId 
        AND name LIKE '%' || :query || '%'
        ORDER BY createdAt DESC
    """)
    fun searchGifts(weddingId: String, query: String): Flow<List<GiftEntity>>

    @Query("UPDATE gifts SET isPurchased = :isPurchased WHERE id = :giftId")
    suspend fun updatePurchaseStatus(giftId: String, isPurchased: Boolean)

    @Query("UPDATE gifts SET purchasedBy = :purchasedBy WHERE id = :giftId")
    suspend fun updatePurchasedBy(giftId: String, purchasedBy: String?)

    @Query("SELECT COUNT(*) FROM gifts WHERE weddingId = :weddingId")
    fun getTotalGiftsCount(weddingId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM gifts WHERE weddingId = :weddingId AND isPurchased = 1")
    fun getPurchasedGiftsCount(weddingId: String): Flow<Int>

    @Query("SELECT SUM(price) FROM gifts WHERE weddingId = :weddingId")
    fun getTotalGiftsValue(weddingId: String): Flow<BigDecimal?>

    @Query("DELETE FROM gifts WHERE weddingId = :weddingId")
    suspend fun deleteWeddingGifts(weddingId: String)
}