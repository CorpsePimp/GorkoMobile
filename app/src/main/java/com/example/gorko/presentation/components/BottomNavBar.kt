package com.example.gorko.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.Handshake
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    onPeopleClick: () -> Unit = {},
    onGiftsClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onVendorsClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val items = listOf(
        Triple(Icons.Filled.People, "Гости", onPeopleClick),
        Triple(Icons.Filled.CardGiftcard, "Подарки", onGiftsClick),
        Triple(Icons.Filled.Home, "Главная", onHomeClick),
        Triple(Icons.Filled.Handshake, "Подрядчики", onVendorsClick),
        Triple(Icons.Filled.Settings, "Настройки", onSettingsClick)
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
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { (icon, contentDesc, handler) ->
                IconButton(onClick = handler) {
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