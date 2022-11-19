package com.example.hellofigma.auto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hellofigma.auto.FocusedTextFieldKey
import com.example.hellofigma.auto.InputValidator
import com.example.hellofigma.auto.InputWrapper

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.ui.focus.FocusDirection
import androidx.lifecycle.SavedStateHandle


@HiltViewModel
class InputValidationAutoViewModel @Inject constructor(
    private val handle: SavedStateHandle
) : ViewModel() {

    private var focusedTextField = handle.getStateFlow("focusedTextField") ?: FocusedTextFieldKey.NAME
        set(value) {
            field = value
            handle.set("focusedTextField", value)
        }

    init {
        if (focusedTextField != FocusedTextFieldKey.NONE) focusOnLastSelectedTextField()
    }

    fun onTextFieldFocusChanged(key: FocusedTextFieldKey, isFocused: Boolean) {
        focusedTextField = if (isFocused) key else FocusedTextFieldKey.NONE
    }

    fun onNameImeActionClick() {
        _events.trySend(ScreenEvent.MoveFocus(FocusDirection.Down))
    }

    fun onContinueClick() {
        viewModelScope.launch(Dispatchers.Default) {
            if (areInputsValid.value) clearFocusAndHideKeyboard()
            ..
        }
    }

    private suspend fun clearFocusAndHideKeyboard() {
        _events.send(ScreenEvent.ClearFocus)
        _events.send(ScreenEvent.UpdateKeyboard(false))
        focusedTextField = FocusedTextFieldKey.NONE
    }

    private fun focusOnLastSelectedTextField() {
        viewModelScope.launch(Dispatchers.Default) {
            _events.send(ScreenEvent.RequestFocus(focusedTextField))
            delay(250)
            _events.send(ScreenEvent.UpdateKeyboard(true))
        }
    }
}