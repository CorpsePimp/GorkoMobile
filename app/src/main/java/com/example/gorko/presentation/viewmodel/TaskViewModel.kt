package com.example.gorko.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorko.data.local.entity.TaskEntity
import com.example.gorko.data.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

enum class TaskFilter { ACTIVE, DONE }

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private val _filter = MutableStateFlow(TaskFilter.ACTIVE)
    val filter: StateFlow<TaskFilter> = _filter.asStateFlow()

    private val _tasks = repository.getAll()
        .map { list ->
            list.sortedWith(compareBy<TaskEntity> { it.isDone }
                .thenBy { it.date }
                .thenBy { it.id })
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val activeTasks: StateFlow<List<TaskEntity>> = _tasks.map { it.filter { !it.isDone } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
    val doneTasks: StateFlow<List<TaskEntity>> = _tasks.map { it.filter { it.isDone } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun setFilter(filter: TaskFilter) {
        _filter.value = filter
    }

    fun addTask(title: String, date: LocalDate) = viewModelScope.launch {
        repository.insert(TaskEntity(title = title, date = date.toString()))
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
    }

    fun setDone(task: TaskEntity, done: Boolean) = viewModelScope.launch {
        repository.update(task.copy(isDone = done))
    }
}