package com.example.composables.conocimiento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateExamples(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        // Ejemplo de estado con un Int
        var contador by remember { mutableStateOf(0) }
        Text(text = "Contador: $contador")
        Button(onClick = { contador++ }) {
            Text(text = "Incrementar")
        }

        // Ejemplo de estado con un String
        var texto by remember { mutableStateOf("Texto inicial") }
        Text(text = "Texto: $texto")
        Button(onClick = { texto = "Texto actualizado" }) {
            Text(text = "Actualizar texto")
        }

        // Ejemplo de estado con un Boolean
        var toggle by remember { mutableStateOf(false) }
        Text(text = "Estado del toggle: $toggle")
        Button(onClick = { toggle = !toggle }) {
            Text(text = if (toggle) "Desactivar" else "Activar")
        }
    }
}
