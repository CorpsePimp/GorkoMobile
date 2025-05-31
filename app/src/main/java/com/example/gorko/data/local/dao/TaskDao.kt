package com.example.gorko.data.local.dao

import androidx.room.*
import com.example.gorko.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: TaskEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTasks(tasks: List<TaskEntity>)

    @Update
    suspend fun updateTask(task: TaskEntity)

    @Delete
    suspend fun deleteTask(task: TaskEntity)

    @Query("DELETE FROM tasks WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: String)

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    suspend fun getTaskById(taskId: String): TaskEntity?

    @Query("SELECT * FROM tasks WHERE weddingId = :weddingId ORDER BY dueDateTime ASC")
    fun getWeddingTasks(weddingId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE weddingId = :weddingId AND isCompleted = 0 ORDER BY dueDateTime ASC")
    fun getIncompleteTasks(weddingId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE weddingId = :weddingId AND date(dueDateTime) = date(:date) ORDER BY dueDateTime ASC")
    fun getTasksForDate(weddingId: String, date: Long): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE weddingId = :weddingId AND date(dueDateTime) = date('now', 'localtime') ORDER BY dueDateTime ASC")
    fun getTodayTasks(weddingId: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM tasks WHERE weddingId = :weddingId AND isCompleted = 0 AND dueDateTime < :dateTime ORDER BY dueDateTime ASC")
    fun getOverdueTasks(weddingId: String, dateTime: Long): Flow<List<TaskEntity>>

    @Query("UPDATE tasks SET isCompleted = :isCompleted WHERE id = :taskId")
    suspend fun toggleTaskCompletion(taskId: String, isCompleted: Boolean)

    @Query("DELETE FROM tasks WHERE weddingId = :weddingId")
    suspend fun deleteWeddingTasks(weddingId: String)

    @Query("SELECT COUNT(*) FROM tasks WHERE weddingId = :weddingId AND isCompleted = 0")
    fun getIncompleteTasksCount(weddingId: String): Flow<Int>
}