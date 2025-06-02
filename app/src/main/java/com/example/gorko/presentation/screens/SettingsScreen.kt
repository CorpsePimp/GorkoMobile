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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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

@Composable
fun SettingsScreen(
    onLogout: () -> Unit = {},
    onMainClick: () -> Unit = {}
) {
    Column(
        Modifier
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
                text = "Настройки",
                color = Color(0xFFC58C94),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        Spacer(Modifier.height(16.dp))

        // Wedding Profile Card
        Row(
            Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(52.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFAD9E1)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "СС", // Initials of "Светлана и Савва"
                    color = Color(0xFFC58C94),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }
            Spacer(Modifier.width(16.dp))
            Column {
                Text(
                    text = "Светлана и Савва",
                    color = Color(0xFFC58C94),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Свадьба: 2 августа 2025",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Settings List
        SettingsItem("Язык", "Русский")
        SettingsItem("Уведомления", "Включены")
        SettingsItem("Тема", "Светлая")
        SettingsItem("Резервное копирование")
        SettingsItem("О приложении")

        Spacer(Modifier.weight(1f))

        // Logout Button
        Box(
            Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFAD9E1),
                    contentColor = Color(0xFFC58C94)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(
                    "Выйти из аккаунта",
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFFC58C94)
                )
            }
        }

        // Bottom Bar for settings
        SettingsBottomBar(onMainClick = onMainClick)
    }
}

@Composable
fun SettingsItem(
    title: String,
    value: String? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 6.dp)
            .background(Color(0xFFF6F6F6), RoundedCornerShape(12.dp))
            .clickable(enabled = onClick != null) { onClick?.invoke() }
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color(0xFFC58C94),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.weight(1f)
        )
        if (value != null) {
            Text(
                text = value,
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
            Text(
                text = " ›",
                color = Color.Gray,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Composable
fun SettingsBottomBar(onMainClick: () -> Unit = {}) {
    val items = listOf(
        Icons.Filled.People,
        Icons.Filled.CardGiftcard,
        Icons.Filled.Home, // <-- "Главная"
        Icons.Filled.Handshake,
        Icons.Filled.Settings
    )
    Row(
        Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items.forEachIndexed { idx, icon ->
            IconButton(onClick = {
                if (idx == 2) onMainClick() // "Главная" — третья кнопка
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color(0xFFC58C94),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}