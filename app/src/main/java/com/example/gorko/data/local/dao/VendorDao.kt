package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.VendorEntity
import com.example.gorko.data.local.entity.VendorCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface VendorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendor(vendor: VendorEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVendors(vendors: List<VendorEntity>)

    @Update
    suspend fun updateVendor(vendor: VendorEntity)

    @Delete
    suspend fun deleteVendor(vendor: VendorEntity)

    @Query("SELECT * FROM vendors WHERE id = :vendorId")
    suspend fun getVendorById(vendorId: String): VendorEntity?

    @Query("SELECT * FROM vendors ORDER BY rating DESC, name ASC")
    fun getAllVendors(): Flow<List<VendorEntity>>

    @Query("SELECT * FROM vendors WHERE category = :category ORDER BY rating DESC, name ASC")
    fun getVendorsByCategory(category: VendorCategory): Flow<List<VendorEntity>>

    @Query("""
        SELECT * FROM vendors 
        WHERE (name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%')
        ORDER BY rating DESC, name ASC
    """)
    fun searchVendors(query: String): Flow<List<VendorEntity>>

    @Query("""
        SELECT * FROM vendors 
        WHERE category = :category 
        AND (name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%')
        ORDER BY rating DESC, name ASC
    """)
    fun searchVendorsInCategory(category: VendorCategory, query: String): Flow<List<VendorEntity>>

    @Query("""
        SELECT * FROM vendors 
        WHERE city = :city 
        ORDER BY rating DESC, name ASC
    """)
    fun getVendorsByCity(city: String): Flow<List<VendorEntity>>

    @Query("""
        SELECT * FROM vendors 
        WHERE category = :category AND city = :city 
        ORDER BY rating DESC, name ASC
    """)
    fun getVendorsByCategoryAndCity(category: VendorCategory, city: String): Flow<List<VendorEntity>>

    @Query("SELECT DISTINCT city FROM vendors WHERE city IS NOT NULL ORDER BY city ASC")
    fun getAllCities(): Flow<List<String>>

    @Query("UPDATE vendors SET isFavorite = :isFavorite WHERE id = :vendorId")
    suspend fun updateFavoriteStatus(vendorId: String, isFavorite: Boolean)

    @Query("SELECT * FROM vendors WHERE isFavorite = 1 ORDER BY rating DESC, name ASC")
    fun getFavoriteVendors(): Flow<List<VendorEntity>>

    @Query("DELETE FROM vendors")
    suspend fun clearVendors()
}