package com.example.composables.conocimiento

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavegacionPantallas(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "pantalla1", modifier = modifier) {
        composable("pantalla1") {
            PantallaUno(onNavigate = { navController.navigate("pantalla2") })
        }
        composable("pantalla2") {
            PantallaDos(onNavigateBack = { navController.popBackStack() })
        }
    }
}

@Composable
fun PantallaUno(onNavigate: () -> Unit) {
    Button(onClick = onNavigate) {
        Text("Ir a Pantalla 2")
    }
}

@Composable
fun PantallaDos(onNavigateBack: () -> Unit) {
    Button(onClick = onNavigateBack) {
        Text("Volver a Pantalla 1")
    }
}
