package com.example.composables.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormularioViewModel : ViewModel() {

    val input1: MutableState<String> = mutableStateOf("")
    val input2: MutableState<String> = mutableStateOf("")

    val input1Error: MutableState<String?> = mutableStateOf(null)
    val input2Error: MutableState<String?> = mutableStateOf(null)

    fun validateInputs(): Boolean {
        var isValid = true

        if (input1.value.isBlank()) {
            input1Error.value = "El campo 1 no puede estar vacío"
            isValid = false
        } else {
            input1Error.value = null
        }

        if (input2.value.isBlank()) {
            input2Error.value = "El campo 2 no puede estar vacío"
            isValid = false
        } else {
            input2Error.value = null
        }

        return isValid
    }

    fun submitForm(): Boolean {
        return if (validateInputs()) {
            // Aquí puedes manejar la lógica de envío del formulario
            true
        } else {
            false
        }
    }
}
