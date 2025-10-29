package com.example.composables.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CharacterScreen(modifier: Modifier = Modifier, viewModel: CharacterViewModel = viewModel()) {
    val characters = viewModel.characters.value // Accedo al valor de State para obtener la lista de personajes
    val errorMessage = viewModel.errorMessage.value // Accedo al valor de State para obtener el mensaje de error

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Lista de Personajes")
        Spacer(modifier = Modifier.height(16.dp))
        // Verifica si hay un mensaje de error y lo muestra en la pantalla
        if (errorMessage != null) {
            Text(text = errorMessage) // Muestra el mensaje de error si existe
        } else {
            // Muestra la lista de personajes en un LazyColumn
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // Itera sobre la lista de personajes y crea un elemento para cada uno
                items(characters) { character ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        // Muestra el nombre del personaje
                        Text(text = "Nombre: ${character.name}")
                        // Muestra el estado del personaje (vivo, muerto, etc.)
                        Text(text = "Estado: ${character.status}")
                        // Muestra la especie del personaje
                        Text(text = "Especie: ${character.species}")
                        // Muestra el género del personaje
                        Text(text = "Género: ${character.gender}")
                        // Muestra el origen del personaje
                        Text(text = "Origen: ${character.originName}")
                        // Añade un espacio entre los elementos
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                }
            }
        }
    }
}
