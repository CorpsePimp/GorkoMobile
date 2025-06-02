package com.example.gorko.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@Composable
fun AddTaskDialog(
    onAdd: (String, LocalDate) -> Unit,
    onDismiss: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var date by remember { mutableStateOf(LocalDate.now()) }
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Новая задача") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Название задачи") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(16.dp))
                DatePickerField(date = date, onDateChange = { date = it })
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    if (title.isNotBlank()) onAdd(title, date)
                },
                enabled = title.isNotBlank()
            ) { Text("Добавить") }
        },
        dismissButton = { TextButton(onClick = onDismiss) { Text("Отмена") } }
    )
}

// Простейший выбор даты – можно заменить на более красивый, если нужна кастомизация
@Composable
fun DatePickerField(date: LocalDate, onDateChange: (LocalDate) -> Unit) {
    // Место для интеграции MaterialDatePicker или кастомного выбора даты
    OutlinedTextField(
        value = date.toString(),
        onValueChange = { /* не редактировать вручную */ },
        label = { Text("Дата выполнения") },
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
}