package com.example.hellofigma.validation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class MainViewModel : ViewModel() {

        private val _state = MutableStateFlow(MainState())

        val state: StateFlow<MainState>
            get() = _state.asStateFlow()

        fun updatedeviceName(value: String) {
            _state.value = state.value.copy(deviceName = value)
        }
     fun updatePhoneNumber(value: String) {
        _state.value = state.value.copy(devicePhoneNumber = value)
    }


}
