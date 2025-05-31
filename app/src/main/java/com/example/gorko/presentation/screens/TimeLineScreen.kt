package com.example.gorko.presentation.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gorko.presentation.viewmodel.WeddingViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun TimeLineScreen(
    weddingViewModel: WeddingViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val weddingDate by weddingViewModel.weddingDate.collectAsState()
    var selectedDate by remember { mutableStateOf(weddingDate) }

    val today = LocalDate.now()
    val daysLeft = ChronoUnit.DAYS.between(today, selectedDate).coerceAtLeast(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDF6F7))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Верхний бар с кнопкой назад
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBack) {
                Text("Назад", color = Color(0xFFC58C94))
            }
            Spacer(Modifier.weight(1f))
        }

        Spacer(Modifier.height(30.dp))

        // Карточка с днями до свадьбы
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFF9ECED))
                .border(1.dp, Color(0xFFE6D7D9), RoundedCornerShape(20.dp))
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("До свадьбы осталось:", color = Color(0xFFC58C94), fontSize = 16.sp)
                Text(
                    "$daysLeft дней",
                    color = Color(0xFFC58C94),
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )
            }
        }

        Spacer(Modifier.height(40.dp))

        // Кнопка выбора даты
        Button(
            onClick = {
                val date = selectedDate
                DatePickerDialog(
                    context,
                    { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
                    },
                    date.year,
                    date.monthValue - 1,
                    date.dayOfMonth
                ).show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF9ECED),
                contentColor = Color(0xFFC58C94)
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Выбрать дату свадьбы")
        }

        Spacer(Modifier.height(20.dp))

        // Текущая выбранная дата
        Text(
            "Текущая дата свадьбы: ${selectedDate.dayOfMonth}.${selectedDate.monthValue}.${selectedDate.year}",
            color = Color(0xFFC58C94),
            fontSize = 16.sp
        )

        Spacer(Modifier.height(32.dp))

        // Кнопка "Сохранить"
        Button(
            onClick = {
                weddingViewModel.setWeddingDate(selectedDate)
                onBack()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC58C94),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(0.9f)
        ) {
            Text("Сохранить", fontWeight = FontWeight.Bold)
        }
    }
}

