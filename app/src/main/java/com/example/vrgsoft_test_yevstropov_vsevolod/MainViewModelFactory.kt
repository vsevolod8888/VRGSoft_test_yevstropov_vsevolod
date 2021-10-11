package com.example.vrgsoft_test_yevstropov_vsevolod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModelFactory (
    private val repozitory: Repozitory
) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                return MainViewModel(repozitory) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}