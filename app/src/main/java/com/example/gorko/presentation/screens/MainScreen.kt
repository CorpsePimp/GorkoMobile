package com.example.gorko.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun MainScreen(
    onTasksClick: () -> Unit = {},
    onTasksMoreClick: () -> Unit = {},
    onFinanceClick: () -> Unit = {},
    onInspirationClick: () -> Unit = {},
    onInspirationMoreClick: () -> Unit = {},
    onAddClick: () -> Unit = {},
    onTimelineClick: () -> Unit = {},
    daysLeft: Int = 82,
    todayTasks: List<Pair<String, Boolean>> = listOf(
        "Позвонить флористу" to true,
        "Заказать торт" to false,
        "Выбрать музыку" to false
    ),
    totalSpent: String = "185,000 ₽"
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF6F7))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 72.dp), // место под нижнюю навигацию
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(16.dp))
            // Панель-таймер до свадьбы
            TimelinePanel(daysLeft, onTimelineClick)
            Spacer(Modifier.height(12.dp))
            // Панель задач
            TodayTasksPanel(todayTasks, onTasksClick, onTasksMoreClick)
            Spacer(Modifier.height(12.dp))
            // Панель финансового трекера
            FinancePanel(totalSpent, onFinanceClick)
            Spacer(Modifier.height(12.dp))
            // Панель вдохновения
            InspirationPanel(onInspirationClick, onInspirationMoreClick)
        }
        // Кнопка "+"
        FloatingActionButton(
            onClick = onAddClick,
            containerColor = Color(0xFFF8D9DE),
            contentColor = Color(0xFFC58C94),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 32.dp, bottom = 88.dp)
                .zIndex(2f)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Добавить")
        }

        // Нижняя панель навигации
        BottomBar(
            modifier = Modifier
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun TimelinePanel(daysLeft: Int, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8D9DE))
            .clickable { onClick() }
            .padding(18.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "До свадьбы осталось:",
                    color = Color(0xFFC58C94),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
                Text(
                    text = "$daysLeft дней",
                    color = Color(0xFFC58C94),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "calendar",
                tint = Color(0xFFC58C94),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun TodayTasksPanel(
    tasks: List<Pair<String, Boolean>>,
    onPanelClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8D9DE))
            .clickable { onPanelClick() }
            .padding(18.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Задачи на сегодня",
                    color = Color(0xFFC58C94),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Ещё",
                    color = Color(0xFFC58C94),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .clickable { onMoreClick() }
                )
            }
            Spacer(Modifier.height(6.dp))
            for ((task, done) in tasks) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 2.dp)
                ) {
                    Checkbox(
                        checked = done,
                        onCheckedChange = null,
                        colors = CheckboxDefaults.colors(
                            checkedColor = Color(0xFFC58C94),
                            uncheckedColor = Color(0xFFF8D9DE),
                            disabledCheckedColor = Color(0xFFF8D9DE),
                            disabledUncheckedColor = Color(0xFFF8D9DE),
                        ),
                        enabled = false,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = task,
                        color = Color(0xFFC58C94),
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FinancePanel(totalSpent: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8D9DE))
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        Column {
            Text(
                text = "Финансовый трекер",
                color = Color(0xFFC58C94),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = "Потрачено за весь период:",
                color = Color(0xFFC58C94),
                fontSize = 15.sp
            )
            Text(
                text = totalSpent,
                color = Color(0xFFC58C94),
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

@Composable
fun InspirationPanel(
    onClick: () -> Unit,
    onMoreClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xFFF8D9DE))
            .clickable { onClick() }
            .padding(18.dp)
    ) {
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Вдохновение",
                    color = Color(0xFFC58C94),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = "Ещё",
                    color = Color(0xFFC58C94),
                    fontSize = 15.sp,
                    modifier = Modifier
                        .clickable { onMoreClick() }
                )
            }
            Spacer(Modifier.height(6.dp))
            Row {
                repeat(3) {
                    Box(
                        modifier = Modifier
                            .size(54.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFFDF6F7))
                            .padding(4.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
            }
        }
    }
}

@Composable
fun BottomBar(modifier: Modifier = Modifier) {
    // Используем стандартные иконки из material-icons-extended
    val items = listOf(
        Pair(Icons.Filled.People, "Гости"),
        Pair(Icons.Filled.CardGiftcard, "Подарки"),
        Pair(Icons.Filled.Home, "Главная"),
        Pair(Icons.Filled.Handshake, "Подрядчики"),
        Pair(Icons.Filled.Settings, "Настройки")
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White)
    ) {
        Row(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            for ((icon, contentDesc) in items) {
                IconButton(onClick = { /* TODO: handle nav */ }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = contentDesc,
                        tint = Color(0xFFC58C94),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}