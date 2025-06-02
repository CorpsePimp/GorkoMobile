package com.example.gorko.data.repository

import com.example.gorko.data.local.dao.TaskDao
import com.example.gorko.data.local.entity.TaskEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val dao: TaskDao
) {
    fun getAll(): Flow<List<TaskEntity>> = dao.getAll()
    suspend fun insert(task: TaskEntity) = dao.insert(task)
    suspend fun update(task: TaskEntity) = dao.update(task)
    suspend fun delete(task: TaskEntity) = dao.delete(task)
}