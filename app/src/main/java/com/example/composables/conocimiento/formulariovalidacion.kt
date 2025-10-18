package com.example.composables.conocimiento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

/**
 * Formulario con validacion de email y contraseña.
 * Muestra mensajes de error cuando los datos no son validos.
 */
@Composable
fun FormularioValidacion(modifier: Modifier = Modifier) {
    // Estados para los valores de los campos
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estados para los mensajes de error
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    // Estado para mostrar si el formulario fue enviado correctamente
    var mensajeExito by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(16.dp)
    ) {
        // Campo de email
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = "" // Limpiar error al escribir
            },
            label = { Text("Email") },
            isError = emailError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error del email
        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de contraseña
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = "" // Limpiar error al escribir
            },
            label = { Text("Contraseña") },
            isError = passwordError.isNotEmpty(),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error de la contraseña
        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = Color.Red,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Boton para enviar el formulario
        Button(
            onClick = {
                // Limpiar mensajes previos
                emailError = ""
                passwordError = ""
                mensajeExito = ""

                // Validar email
                if (email.isBlank()) {
                    emailError = "El email no puede estar vacio"
                } else if (!email.contains("@") || !email.contains(".")) {
                    emailError = "El email no es valido"
                }

                // Validar contraseña
                if (password.isBlank()) {
                    passwordError = "La contraseña no puede estar vacia"
                } else if (password.length < 6) {
                    passwordError = "La contraseña debe tener al menos 6 caracteres"
                }

                // Si no hay errores, mostrar mensaje de exito
                if (emailError.isEmpty() && passwordError.isEmpty()) {
                    mensajeExito = "Formulario enviado correctamente"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enviar")
        }

        // Mensaje de exito
        if (mensajeExito.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = mensajeExito,
                color = Color.Green,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
