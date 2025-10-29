import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker

@Composable
fun LocationScreen(modifier: Modifier = Modifier) {
    // Contexto de la aplicación para acceder a servicios del sistema
    val context = LocalContext.current

    // Estado para almacenar la ubicación obtenida
    var location by remember { mutableStateOf<Location?>(null) }

    // Estado para manejar mensajes de error
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Lanzador para solicitar permisos de ubicación al usuario
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        // Si el permiso no es concedido, se muestra un mensaje de error
        if (!isGranted) {
            errorMessage = "Permiso de ubicación no concedido. Por favor, habilítalo en la configuración."
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize() // Hace que la columna ocupe todo el tamaño disponible
            .padding(16.dp), // Aplica un margen interno uniforme
        verticalArrangement = Arrangement.Center, // Centra los elementos verticalmente
        horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
    ) {
        // Botón para obtener la ubicación del dispositivo
        Button(onClick = {
            // Verifica si el permiso de ubicación está concedido
            val permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            )

            if (permission == PermissionChecker.PERMISSION_GRANTED) {
                // Si el permiso está concedido, intenta obtener la última ubicación conocida
                val locationManager =
                    context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                @SuppressLint("MissingPermission") // Suprime el aviso de permisos porque ya se verificó
                location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)

                // Si no se puede obtener la ubicación, muestra un mensaje de error
                errorMessage = if (location == null) "No se pudo obtener la ubicación" else null
            } else {
                // Si el permiso no está concedido, solicita el permiso al usuario
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }) {
            Text(text = "Obtener ubicación") // Etiqueta del botón
        }

        Spacer(modifier = Modifier.height(16.dp)) // Espaciado entre elementos

        // Muestra la ubicación obtenida si está disponible
        location?.let {
            Text(text = "Latitud: ${it.latitude}")
            Text(text = "Longitud: ${it.longitude}")
        }

        // Muestra un mensaje de error si existe
        errorMessage?.let {
            Text(text = it, color = androidx.compose.ui.graphics.Color.Red)
        }

        // Si el permiso no está concedido, muestra un botón para abrir la configuración
        if (errorMessage == "Permiso de ubicación no concedido. Por favor, habilítalo en la configuración.") {
            Spacer(modifier = Modifier.height(16.dp)) // Espaciado adicional
            Button(onClick = {
                // Abre la configuración de ubicación del dispositivo
                context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            }) {
                Text(text = "Abrir configuración") // Etiqueta del botón
            }
        }
    }
}
