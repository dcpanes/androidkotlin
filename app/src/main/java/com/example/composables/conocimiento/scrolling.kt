package com.example.composables.conocimiento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Archivo: scrolling.kt
 *
 * Contiene un Composable que muestra una lista de elementos con desplazamiento vertical.
 * Los comentarios explican el uso de rememberScrollState, Modifier.fillMaxSize y verticalScroll.
 */

/**
 * Composable principal que muestra una columna desplazable.
 *
 * - Se crea un estado de scroll con rememberScrollState para conservar la posición
 *   al recomponer.
 * - Se aplica Modifier.fillMaxSize para dar una restricción de tamaño al Column.
 *   Sin una restricción (por ejemplo cuando el Column se adapta al contenido),
 *   el scroll no se activará porque no hay área limitada que desplazar.
 * - Se aplica verticalScroll con el estado creado para habilitar el desplazamiento.
 */
@Composable
fun Scrolling() {
    val sScroll = rememberScrollState()
    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(sScroll)
    ) {
        for (i in 1..200) {
            Text("Item $i")
        }
    }
}