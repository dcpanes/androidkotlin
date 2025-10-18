package com.example.composables.conocimiento

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Muestra tres animaciones populares: Fade, Slide y Scale.
 */
@Composable
fun AnimacionesEjemplo(modifier: Modifier = Modifier) {
    var visible by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { visible = !visible }) {
            Text(if (visible) "Ocultar" else "Mostrar")
        }

        // Animación de desvanecimiento (Fade)
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text("Animación Fade (desvanecimiento)")
        }

        // Animación de desplazamiento (Slide)
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally { it },
            exit = slideOutHorizontally { it }
        ) {
            Text("Animación Slide (desplazamiento horizontal)")
        }

        // Animación de escala (Scale)
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(initialScale = 0.5f),
            exit = scaleOut(targetScale = 0.5f)
        ) {
            Text("Animación Scale (escala)")
        }
    }
}
