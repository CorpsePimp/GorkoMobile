package com.example.gorko.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberDismissState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorko.presentation.viewmodel.Expense
import com.example.gorko.presentation.viewmodel.FinanceViewModel
import java.text.NumberFormat
import java.time.format.DateTimeFormatter
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FinanceScreen(
    financeViewModel: FinanceViewModel,
    onBack: () -> Unit
) {
    val expenses by financeViewModel.expenses.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val spent = expenses.sumOf { it.amount }
    val currencyFormat = NumberFormat.getNumberInstance(Locale("ru", "RU")).apply { maximumFractionDigits = 0 }
    val formatter = DateTimeFormatter.ofPattern("d MMMM", Locale("ru"))

    val topCategories = expenses.groupBy { it.category }
        .mapValues { entry -> entry.value.sumOf { e -> e.amount } }
        .entries.sortedByDescending { it.value }
        .take(3)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF6F7))
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Верхний бар
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFF9ECED))
                    .padding(vertical = 20.dp, horizontal = 16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("Финансы", color = Color(0xFFC58C94), fontSize = 20.sp)
                    Spacer(Modifier.weight(1f))
                    IconButton(onClick = { showDialog = true }) {
                        Icon(Icons.Default.Add, contentDescription = "Добавить", tint = Color(0xFFC58C94))
                    }
                }
            }
            Spacer(Modifier.height(20.dp))

            // Потрачено (фон FAFAFA)
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFAFAFA))
                    .border(1.dp, Color(0xFFE6D7D9), RoundedCornerShape(16.dp))
                    .padding(18.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Потрачено:", color = Color(0xFFC58C94), fontSize = 16.sp)
                    Spacer(Modifier.width(6.dp))
                    Text(
                        "${currencyFormat.format(spent)} ₽",
                        color = Color(0xFFC58C94),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
            Spacer(Modifier.height(18.dp))

            // Диаграмма (фон FAFAFA)
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFFAFAFA))
                    .border(1.dp, Color(0xFFE6D7D9), RoundedCornerShape(16.dp))
                    .padding(20.dp)
            ) {
                if (expenses.isEmpty()) {
                    Box(
                        Modifier
                            .size(90.dp)
                            .align(Alignment.Center)
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize()) {
                            drawCircle(color = Color(0xFFF9ECED), radius = size.minDimension / 2)
                        }
                    }
                } else {
                    PieChart(
                        data = topCategories.map { it.value.toFloat() },
                        colors = listOf(
                            Color(0xFFF9ECED), Color(0xFFD7B0BB), Color(0xFFEADBE2)
                        ),
                        modifier = Modifier.size(90.dp)
                    )
                }
                // Легенда справа
                Column(Modifier.align(Alignment.CenterEnd)) {
                    topCategories.forEachIndexed { i, entry ->
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                Modifier
                                    .size(12.dp)
                                    .background(color = listOf(Color(0xFFF9ECED), Color(0xFFD7B0BB), Color(0xFFEADBE2))[i], shape = CircleShape)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                entry.key,
                                color = Color(0xFFC58C94),
                                fontSize = 15.sp
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Последние расходы", color = Color(0xFFC58C94), fontSize = 17.sp)
            Spacer(Modifier.height(10.dp))

            if (expenses.isEmpty()) {
                Text("Нет расходов", color = Color(0xFFC58C94), fontSize = 15.sp)
            } else {
                Column(Modifier.weight(1f, fill = false)) {
                    expenses.forEach { expense ->
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    financeViewModel.removeExpense(expense)
                                }
                                true
                            }
                        )
                        SwipeToDismiss(
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                Box(
                                    Modifier
                                        .fillMaxSize()
                                        .background(Color(0xFFF8D9DE))
                                        .padding(16.dp),
                                    contentAlignment = Alignment.CenterEnd
                                ) {
                                    Text("Удалить", color = Color(0xFFC58C94))
                                }
                            },
                            dismissContent = {
                                Box(
                                    Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(Color.White)
                                        .border(1.dp, Color(0xFFE6D7D9), RoundedCornerShape(16.dp))
                                        .padding(14.dp)
                                        .padding(bottom = 8.dp)
                                ) {
                                    Column {
                                        Row {
                                            Text(
                                                expense.title,
                                                color = Color(0xFFC58C94),
                                                fontWeight = FontWeight.Medium,
                                                fontSize = 16.sp,
                                                modifier = Modifier.weight(1f)
                                            )
                                            Text(
                                                "${currencyFormat.format(expense.amount)} ₽",
                                                color = Color(0xFFC58C94),
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 16.sp
                                            )
                                        }
                                        Spacer(Modifier.height(2.dp))
                                        Text(
                                            expense.date.format(formatter),
                                            color = Color(0xFFC58C94),
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }
                        )
                        Spacer(Modifier.height(8.dp))
                    }
                }
            }
        }

        // Диалог добавления траты
        if (showDialog) {
            AddExpenseDialog(
                onDismiss = { showDialog = false },
                onAdd = { title, amount, category ->
                    if (title.isNotBlank() && amount > 0 && category.isNotBlank()) {
                        financeViewModel.addExpense(
                            Expense(title = title, amount = amount, category = category)
                        )
                        showDialog = false
                    }
                }
            )
        }
    }
}

@Composable
fun PieChart(data: List<Float>, colors: List<Color>, modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val total = data.sum()
        var startAngle = -90f
        data.forEachIndexed { i, value ->
            val sweep = if (total == 0f) 0f else value / total * 360f
            drawArc(
                color = colors.getOrElse(i) { Color.Gray },
                startAngle = startAngle,
                sweepAngle = sweep,
                useCenter = true
            )
            startAngle += sweep
        }
    }
}