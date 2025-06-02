package com.example.gorko.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.gorko.presentation.viewmodel.InspirationViewModel

@Composable
fun InspirationScreen(
    viewModel: InspirationViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    var query by remember { mutableStateOf("wedding") }
    val photos by viewModel.photos.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadInitial(query) }

    Column(Modifier.fillMaxSize().background(Color.White)) {
        Row(Modifier.padding(16.dp)) {
            TextField(
                value = query,
                onValueChange = { query = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Поиск (например, цветы)") }
            )
            Button(
                onClick = { viewModel.loadInitial(query) },
                modifier = Modifier.padding(start = 8.dp)
            ) { Text("Поиск") }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(8.dp)
        ) {
            itemsIndexed(photos.chunked(3)) { idx, chunk ->
                Row {
                    chunk.forEach { url ->
                        Image(
                            painter = rememberAsyncImagePainter(url),
                            contentDescription = null,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                                .padding(4.dp)
                        )
                    }
                    repeat(3 - chunk.size) { Spacer(Modifier.weight(1f)) }
                }
                // Infinite scroll: если это последняя строка — подгрузи еще
                if (idx == photos.chunked(3).lastIndex) {
                    LaunchedEffect(photos.size) { viewModel.loadMore() }
                }
            }
        }
    }
}