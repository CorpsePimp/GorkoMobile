package com.example.gorko.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class WeddingViewModel @Inject constructor() : ViewModel() {
    private val _weddingDate = MutableStateFlow(LocalDate.now().plusMonths(3))
    val weddingDate: StateFlow<LocalDate> = _weddingDate

    fun setWeddingDate(date: LocalDate) {
        _weddingDate.value = date
    }
}