package com.example.gorko.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gorko.data.local.entity.TaskEntity
import com.example.gorko.presentation.viewmodel.TaskFilter
import com.example.gorko.presentation.viewmodel.TaskViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    viewModel: TaskViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val filter by viewModel.filter.collectAsState()
    val activeTasks by viewModel.activeTasks.collectAsState()
    val doneTasks by viewModel.doneTasks.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Список задач", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Назад")
                    }
                },
                actions = {
                    IconButton(onClick = { showDialog = true }) {
                        Icon(Icons.Default.Add, "Добавить")
                    }
                }
            )
        }
    ) { padding ->
        Column(Modifier.padding(padding).fillMaxSize()) {
            Row(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                FilterChip("Активные", filter == TaskFilter.ACTIVE) { viewModel.setFilter(TaskFilter.ACTIVE) }
                Spacer(Modifier.width(8.dp))
                FilterChip("Завершённые", filter == TaskFilter.DONE) { viewModel.setFilter(TaskFilter.DONE) }
            }
            val tasks = if (filter == TaskFilter.ACTIVE) activeTasks else doneTasks
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(tasks, key = { it.id }) { task ->
                    TaskItem(
                        task = task,
                        onCheckedChange = { checked -> viewModel.setDone(task, checked) },
                        onDelete = { viewModel.deleteTask(task) },
                        isDone = task.isDone
                    )
                }
            }
        }
    }
    if (showDialog) {
        AddTaskDialog(
            onAdd = { title, date ->
                viewModel.addTask(title, date)
                showDialog = false
            },
            onDismiss = { showDialog = false }
        )
    }
}

@Composable
fun FilterChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Surface(
        color = if (selected) Color(0xFFFAD9E1) else Color(0xFFF6F6F6),
        shape = MaterialTheme.shapes.medium,
        tonalElevation = if (selected) 2.dp else 0.dp,
        modifier = Modifier.clickable(onClick = onClick)
    ) {
        Text(
            text,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            color = if (selected) Color(0xFFC58C94) else Color.Gray
        )
    }
}

@Composable
fun TaskItem(
    task: TaskEntity,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: () -> Unit,
    isDone: Boolean
) {
    var offsetX by remember { mutableStateOf(0f) }
    Box(
        Modifier
            .pointerInput(task.id) {
                detectHorizontalDragGestures { _, dragAmount ->
                    offsetX += dragAmount
                    if (offsetX < -100) {
                        onDelete()
                        offsetX = 0f
                    }
                }
            }
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            tonalElevation = 2.dp,
            color = Color(0xFFF6F6F6),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp)
        ) {
            Row(
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isDone,
                    onCheckedChange = onCheckedChange,
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFFC58C94))
                )
                Spacer(Modifier.width(12.dp))
                Column(Modifier.weight(1f)) {
                    Text(task.title, color = Color(0xFFC58C94), fontWeight = FontWeight.Medium)
                    val today = LocalDate.now()
                    val dateStr = if (task.date == today.toString()) "Сегодня"
                        else LocalDate.parse(task.date).format(DateTimeFormatter.ofPattern("d MMMM"))
                    Text(dateStr, color = Color.Gray, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}