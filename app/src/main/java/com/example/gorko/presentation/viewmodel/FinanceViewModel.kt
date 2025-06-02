package com.example.gorko.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorko.data.local.entity.ExpenseEntity
import com.example.gorko.data.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

data class Expense(
    val id: Long = 0,
    val title: String,
    val amount: Double,
    val category: String,
    val date: LocalDate = LocalDate.now()
)

fun ExpenseEntity.toDomain(): Expense =
    Expense(id, title, amount, category, LocalDate.parse(date))
fun Expense.toEntity(): ExpenseEntity =
    ExpenseEntity(id, title, amount, category, date.toString())

@HiltViewModel
class FinanceViewModel @Inject constructor(
    private val repository: ExpenseRepository
) : ViewModel() {
    private val _expenses = MutableStateFlow<List<Expense>>(emptyList())
    val expenses: StateFlow<List<Expense>> = _expenses.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllExpenses().collect { list ->
                _expenses.value = list.map { it.toDomain() }
            }
        }
    }

    fun addExpense(expense: Expense) {
        viewModelScope.launch {
            repository.insertExpense(expense.toEntity())
        }
    }

    fun removeExpense(expense: Expense) {
        viewModelScope.launch {
            repository.deleteExpense(expense.toEntity())
        }
    }
}