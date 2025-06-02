package com.example.gorko.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gorko.presentation.viewmodel.TaskViewModel
import java.time.LocalDate

@Composable
fun TodayTasksCard(
    onClick: () -> Unit,
    viewModel: TaskViewModel = hiltViewModel()
) {
    val tasks by viewModel.activeTasks.collectAsState()
    val todayTasks = tasks.filter { it.date == LocalDate.now().toString() }

    Box(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        Column {
            Text("Задачи на сегодня", color = Color(0xFFC58C94))
            Spacer(Modifier.height(8.dp))
            if (todayTasks.isEmpty()) {
                Text("Нет задач на сегодня", color = Color.Gray)
            } else {
                todayTasks.forEach {
                    Text(it.title, color = Color.Black)
                }
            }
        }
    }
}