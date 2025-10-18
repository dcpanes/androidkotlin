package com.example.composables.conocimiento

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp

/**
 * Composable que permite tomar una foto con la cámara y mostrarla en pantalla.
 */
@Composable
fun CamaraCapture(modifier: Modifier = Modifier) {
    var foto by remember { mutableStateOf<Bitmap?>(null) }

    // Launcher para abrir la cámara y recibir el resultado
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            foto = imageBitmap
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                launcher.launch(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Abrir cámara")
        }

        Spacer(modifier = Modifier.height(24.dp))

        foto?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Foto capturada",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
        }
    }
}
