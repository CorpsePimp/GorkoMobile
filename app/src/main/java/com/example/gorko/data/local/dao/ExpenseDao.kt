package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.ExpenseEntity
import com.example.gorko.data.local.entity.ExpenseCategory
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: List<ExpenseEntity>)

    @Update
    suspend fun updateExpense(expense: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expense: ExpenseEntity)

    @Query("DELETE FROM expenses WHERE id = :expenseId")
    suspend fun deleteExpenseById(expenseId: String)

    @Query("SELECT * FROM expenses WHERE id = :expenseId")
    suspend fun getExpenseById(expenseId: String): ExpenseEntity?

    @Query("SELECT * FROM expenses WHERE weddingId = :weddingId ORDER BY createdAt DESC")
    fun getWeddingExpenses(weddingId: String): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expenses WHERE weddingId = :weddingId AND category = :category ORDER BY createdAt DESC")
    fun getExpensesByCategory(weddingId: String, category: ExpenseCategory): Flow<List<ExpenseEntity>>

    @Query("SELECT SUM(amount) FROM expenses WHERE weddingId = :weddingId")
    fun getTotalExpenses(weddingId: String): Flow<BigDecimal?>

    @Query("SELECT SUM(amount) FROM expenses WHERE weddingId = :weddingId AND category = :category")
    fun getTotalByCategory(weddingId: String, category: ExpenseCategory): Flow<BigDecimal?>

    @Query("""
        SELECT category, SUM(amount) as total 
        FROM expenses 
        WHERE weddingId = :weddingId 
        GROUP BY category 
        ORDER BY total DESC
    """)
    fun getCategoryTotals(weddingId: String): Flow<List<CategoryTotal>>

    @Query("""
        SELECT category, SUM(amount) as total 
        FROM expenses 
        WHERE weddingId = :weddingId 
        GROUP BY category 
        ORDER BY total DESC 
        LIMIT 3
    """)
    fun getTop3Categories(weddingId: String): Flow<List<CategoryTotal>>

    @Query("DELETE FROM expenses WHERE weddingId = :weddingId")
    suspend fun deleteWeddingExpenses(weddingId: String)
}

data class CategoryTotal(
    val category: ExpenseCategory,
    val total: BigDecimal
)