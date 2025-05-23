package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.GuestEntity
import com.example.gorko.data.local.entity.GuestStatus
import kotlinx.coroutines.flow.Flow

@Dao
interface GuestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuest(guest: GuestEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuests(guests: List<GuestEntity>)

    @Update
    suspend fun updateGuest(guest: GuestEntity)

    @Delete
    suspend fun deleteGuest(guest: GuestEntity)

    @Query("DELETE FROM guests WHERE id = :guestId")
    suspend fun deleteGuestById(guestId: String)

    @Query("SELECT * FROM guests WHERE id = :guestId")
    suspend fun getGuestById(guestId: String): GuestEntity?

    @Query("SELECT * FROM guests WHERE weddingId = :weddingId ORDER BY fullName ASC")
    fun getWeddingGuests(weddingId: String): Flow<List<GuestEntity>>

    @Query("SELECT * FROM guests WHERE weddingId = :weddingId AND status = :status ORDER BY fullName ASC")
    fun getGuestsByStatus(weddingId: String, status: GuestStatus): Flow<List<GuestEntity>>

    @Query("""
        SELECT * FROM guests 
        WHERE weddingId = :weddingId 
        AND (fullName LIKE '%' || :query || '%' OR phone LIKE '%' || :query || '%')
        ORDER BY fullName ASC
    """)
    fun searchGuests(weddingId: String, query: String): Flow<List<GuestEntity>>

    @Query("SELECT COUNT(*) FROM guests WHERE weddingId = :weddingId")
    fun getTotalGuestsCount(weddingId: String): Flow<Int>

    @Query("SELECT COUNT(*) FROM guests WHERE weddingId = :weddingId AND status = :status")
    fun getGuestsCountByStatus(weddingId: String, status: GuestStatus): Flow<Int>

    @Query("UPDATE guests SET status = :status WHERE id = :guestId")
    suspend fun updateGuestStatus(guestId: String, status: GuestStatus)

    @Query("UPDATE guests SET tableNumber = :tableNumber WHERE id = :guestId")
    suspend fun updateGuestTable(guestId: String, tableNumber: Int?)

    @Query("SELECT * FROM guests WHERE weddingId = :weddingId AND tableNumber = :tableNumber ORDER BY fullName ASC")
    fun getGuestsByTable(weddingId: String, tableNumber: Int): Flow<List<GuestEntity>>

    @Query("DELETE FROM guests WHERE weddingId = :weddingId")
    suspend fun deleteWeddingGuests(weddingId: String)
}