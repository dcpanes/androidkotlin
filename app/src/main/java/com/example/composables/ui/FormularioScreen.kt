package com.example.composables.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch

@Composable
fun FormularioScreen(modifier: Modifier = Modifier, viewModel: FormularioViewModel = viewModel()) {
    // Recordamos los estados de los inputs y los errores asociados desde el ViewModel
    val input1 = remember { viewModel.input1 }
    val input2 = remember { viewModel.input2 }
    val input1Error = remember { viewModel.input1Error }
    val input2Error = remember { viewModel.input2Error }

    // Estado para manejar el Snackbar y una corrutina para mostrarlo
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize() // Hace que la columna ocupe todo el tamaño disponible
            .padding(16.dp), // Aplica un padding uniforme alrededor de la columna
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ) {
        // Campo de texto para el primer input con validación visual
        OutlinedTextField(
            value = input1.value, // Valor actual del input
            onValueChange = { input1.value = it }, // Actualiza el estado cuando cambia el texto
            label = { Text("Campo 1") }, // Etiqueta descriptiva del campo
            isError = input1Error.value != null, // Indica si hay un error en el campo
            modifier = Modifier.fillMaxWidth() // Hace que el campo ocupe todo el ancho disponible
        )
        // Muestra un mensaje de error si existe
        if (input1Error.value != null) {
            Text(text = input1Error.value!!, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre los campos

        // Campo de texto para el segundo input con validación visual
        OutlinedTextField(
            value = input2.value,
            onValueChange = { input2.value = it },
            label = { Text("Campo 2") },
            isError = input2Error.value != null,
            modifier = Modifier.fillMaxWidth()
        )
        // Muestra un mensaje de error si existe
        if (input2Error.value != null) {
            Text(text = input2Error.value!!, color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado antes del botón

        // Botón para enviar el formulario
        Button(onClick = {
            if (viewModel.submitForm()) { // Verifica si el formulario es válido
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Formulario enviado correctamente") // Muestra un mensaje de éxito
                }
            }
        }) {
            Text(text = "Enviar") // Texto del botón
        }

        // Host para mostrar el Snackbar
        SnackbarHost(hostState = snackbarHostState)
    }
}
