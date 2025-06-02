package com.example.gorko.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorko.presentation.components.BottomNavBar

data class Vendor(
    val initials: String,
    val name: String,
    val category: String,
    val rating: Double
)

@Composable
fun VendorsScreen(
    onHomeClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onVendorsClick: () -> Unit = {},
) {
    val categories = listOf("Все", "Рестораны", "Фотографы", "Ведущие", "Торты", "Музыканты", "Видео", "Украшения", "Еще...")
    var selectedCategory by remember { mutableStateOf("Все") }
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val allVendors = listOf(
        Vendor("ФР", "Ресторан \"Флёр\"", "Банкетный зал", 4.8),
        Vendor("АФ", "Анна Фаст", "Фотограф", 5.0),
        Vendor("МП", "Михаил Пан", "Ведущий", 4.7),
        Vendor("ДМ", "Джем Музыка", "Музыканты", 4.9),
        Vendor("ВК", "Вкусный Кейс", "Торты", 5.0),
        Vendor("ВС", "ВидеоСъёмка Про", "Видео", 4.6),
        Vendor("ЮК", "Юлия Краснова", "Украшения", 4.8),
        Vendor("ГД", "Гранд Двор", "Ресторан", 4.7),
        Vendor("АМ", "Андрей Мельник", "Ведущий", 4.5),
        Vendor("МК", "Мария Кот", "Фотограф", 4.9)
    )
    val vendors = allVendors.filter {
        (selectedCategory == "Все" || it.category.contains(selectedCategory, true) || it.name.contains(selectedCategory, true)) &&
                (searchQuery.text.isBlank() || it.name.contains(searchQuery.text, true))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // TopBar imitation
        Box(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFFAD9E1))
                .padding(vertical = 18.dp)
        ) {
            Text(
                text = "Подрядчики",
                color = Color(0xFFC58C94),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(12.dp))

        // Поиск (шире, почти на всю ширину)
        Box(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .height(44.dp)
                .background(Color.White, RoundedCornerShape(24.dp))
                .border(1.dp, Color(0xFFF6D6DE), RoundedCornerShape(24.dp)),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "search",
                    tint = Color(0xFFC58C94),
                    modifier = Modifier.padding(start = 12.dp)
                )
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            "Поиск подрядчиков...",
                            color = Color(0xFFB7A8AB),
                            fontSize = 16.sp
                        )
                    },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    ),
                    textStyle = TextStyle(
                        fontSize = 16.sp,
                        color = Color(0xFFC58C94)
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 4.dp)
                )
            }
        }

        Spacer(Modifier.height(14.dp))

        // Категории (горизонтальный скролл)
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(if (selectedCategory == category) Color(0xFFFAD9E1) else Color(0xFFF6F6F6))
                        .border(
                            1.dp,
                            if (selectedCategory == category) Color(0xFFFAD9E1) else Color(0xFFF6F6F6),
                            RoundedCornerShape(20.dp)
                        )
                        .clickable { selectedCategory = category }
                        .padding(horizontal = 18.dp, vertical = 8.dp)
                ) {
                    Text(
                        category,
                        color = if (selectedCategory == category) Color(0xFFC58C94) else Color(0xFFB7A8AB),
                        fontWeight = if (selectedCategory == category) FontWeight.Bold else FontWeight.Normal,
                        fontSize = 15.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Список подрядчиков (карточки на всю ширину)
        Column(
            Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            vendors.forEach { vendor ->
                VendorCardWide(vendor)
                Spacer(Modifier.height(12.dp))
            }
        }

        // Нижний бар
        BottomNavBar(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onHomeClick = onHomeClick,
            onSettingsClick = onSettingsClick,
            onVendorsClick = onVendorsClick,
        )
    }
}

@Composable
fun VendorCardWide(vendor: Vendor) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(16.dp))
            .border(1.dp, Color(0xFFF6F6F6), RoundedCornerShape(16.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            Modifier
                .size(46.dp)
                .clip(CircleShape)
                .background(Color(0xFFFAD9E1)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                vendor.initials,
                color = Color(0xFFC58C94),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        }
        Spacer(Modifier.width(16.dp))
        Column(
            Modifier.weight(1f)
        ) {
            Text(
                vendor.name,
                color = Color(0xFFC58C94),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                vendor.category,
                color = Color(0xFFB7A8AB),
                fontSize = 14.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    "★".repeat(5),
                    color = Color(0xFFF7BFCF),
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    vendor.rating.toString(),
                    color = Color(0xFFC58C94),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}