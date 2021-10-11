package com.example.vrgsoft_test_yevstropov_vsevolod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vrgsoft_test_yevstropov_vsevolod.repozitory.Repozitory
import kotlinx.coroutines.launch

class MainViewModel(val repozitory: Repozitory) : ViewModel() {
    init {
        viewModelScope.launch {
            suspend fun refreshList() {

            }
        }
    }
}