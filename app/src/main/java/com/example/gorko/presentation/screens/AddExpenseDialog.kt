package com.example.gorko.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val staticCategories = listOf(
    "Регистрация", "Площадка", "Еда и напитки", "Декорации и цветы",
    "Фото и видео", "Музыка и ведущий", "Одежда и образ", "Кольца и украшения",
    "Приглашения и сувениры", "Транспорт и проживание", "Организатор / Вендор",
    "Развлечения", "Остальное"
)

@Composable
fun AddExpenseDialog(
    onDismiss: () -> Unit,
    onAdd: (String, Double, String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var category by remember { mutableStateOf(staticCategories[0]) }
    var categoryDropdownExpanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    val amountValue = amount.replace(',', '.').toDoubleOrNull() ?: 0.0
                    onAdd(title, amountValue, category)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC58C94))
            ) {
                Text("Добавить", color = Color.White)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) { Text("Отмена", color = Color(0xFFC58C94)) }
        },
        title = {
            Text("Новая трата", color = Color(0xFFC58C94))
        },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Название", color = Color(0xFFC58C94)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it.filter { c -> c.isDigit() || c == '.' || c == ',' } },
                    label = { Text("Сумма", color = Color(0xFFC58C94)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(10.dp))
                Box(
                    Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFFE6D7D9), RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .clickable { categoryDropdownExpanded = true }
                        .padding(12.dp)
                ) {
                    Text(category, color = Color(0xFFC58C94))
                    DropdownMenu(
                        expanded = categoryDropdownExpanded,
                        onDismissRequest = { categoryDropdownExpanded = false }
                    ) {
                        staticCategories.forEach {
                            DropdownMenuItem(
                                text = { Text(it, color = Color(0xFFC58C94)) },
                                onClick = {
                                    category = it
                                    categoryDropdownExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        containerColor = Color(0xFFF9ECED),
        shape = RoundedCornerShape(20.dp)
    )
}