package com.example.gorko.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorko.data.repository.PexelsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InspirationViewModel @Inject constructor(
    private val repo: PexelsRepository
) : ViewModel() {
    private val _photos = MutableStateFlow<List<String>>(emptyList())
    val photos: StateFlow<List<String>> = _photos
    private var page = 1
    private var lastQuery = "wedding"
    private var isLoading = false

    fun loadInitial(query: String = "wedding") {
        page = 1
        lastQuery = query
        _photos.value = emptyList()
        loadMore()
    }

    fun loadMore() {
        if (isLoading) return
        isLoading = true
        viewModelScope.launch {
            val newPhotos = repo.getPhotos(lastQuery, page)
            _photos.value = _photos.value + newPhotos
            page++
            isLoading = false
        }
    }
}