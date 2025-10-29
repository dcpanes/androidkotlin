package com.example.composables.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class CharacterViewModel : ViewModel() {

    private val _characters = mutableStateOf<List<Character>>(emptyList())
    val characters: State<List<Character>> = _characters

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private fun parseCharacters(response: String): List<Character> {
        val jsonResponse = JSONObject(response) // Convierte la respuesta en un objeto JSON
        val results = jsonResponse.getJSONArray("results") // Obtiene el array de resultados

        val tempCharacters = mutableListOf<Character>() // Lista temporal para almacenar los personajes
        for (i in 0 until results.length()) { // Itera sobre cada elemento del array
            val characterJson = results.getJSONObject(i) // Obtiene el objeto JSON de cada personaje
            val character = Character(
                id = characterJson.getInt("id"), // Obtiene el ID del personaje
                name = characterJson.getString("name"), // Obtiene el nombre del personaje
                status = characterJson.getString("status"), // Obtiene el estado del personaje
                species = characterJson.getString("species"), // Obtiene la especie del personaje
                gender = characterJson.getString("gender"), // Obtiene el género del personaje
                originName = characterJson.getJSONObject("origin").getString("name") // Obtiene el nombre del origen del personaje
            )
            tempCharacters.add(character) // Añade el personaje a la lista temporal
        }
        return tempCharacters
    }

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) { // Lanza una corrutina en el contexto IO para realizar operaciones de red
            try {
                val url = URL("https://rickandmortyapi.com/api/character") // URL de la API
                val connection = url.openConnection() as HttpURLConnection // Abre la conexión HTTP

                // Usar Reflection para establecer propiedades privadas
                val methodField = HttpURLConnection::class.java.getDeclaredField("method") // Obtiene el campo privado "method"
                methodField.isAccessible = true // Permite el acceso al campo privado
                methodField.set(connection, "GET") // Establece el método HTTP como GET

                connection.connectTimeout = 5000 // Tiempo máximo para establecer la conexión (en milisegundos)
                connection.readTimeout = 5000 // Tiempo máximo para leer la respuesta (en milisegundos)
                connection.setRequestProperty("Accept", "application/json") // Solicita que la respuesta sea en formato JSON

                if (connection.responseCode == HttpURLConnection.HTTP_OK) { // Verifica si la respuesta fue exitosa (código 200)
                    val response = connection.inputStream.bufferedReader().use { it.readText() } // Lee la respuesta como texto
                    val characters = parseCharacters(response) // Llama al método para procesar los personajes

                    withContext(Dispatchers.Main) { // Cambia al contexto Main para actualizar la UI
                        _characters.value = characters // Actualiza la lista de personajes
                        _errorMessage.value = null // Limpia cualquier mensaje de error
                    }
                } else {
                    withContext(Dispatchers.Main) { // Cambia al contexto Main para manejar el error
                        _errorMessage.value = "Error en la conexión: Código ${connection.responseCode}" // Muestra el código de error
                    }
                }
            } catch (e: Exception) { // Captura cualquier excepción que ocurra durante la operación
                withContext(Dispatchers.Main) { // Cambia al contexto Main para manejar el error
                    _errorMessage.value = "Error al obtener los personajes: ${e.message}" // Muestra el mensaje de error
                }
            }
        }
    }
}

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val originName: String
)
