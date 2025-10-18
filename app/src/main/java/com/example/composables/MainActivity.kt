package com.example.composables

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.composables.conocimiento.CamaraCapture
import com.example.composables.conocimiento.CardConImagen
import com.example.composables.conocimiento.ColumnAndRows
import com.example.composables.conocimiento.FormularioValidacion
import com.example.composables.conocimiento.ListaElementos
import com.example.composables.conocimiento.NavegacionPantallas
import com.example.composables.conocimiento.Scrolling
import androidx.compose.ui.res.painterResource
import com.example.composables.conocimiento.AnimacionesEjemplo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold {
                innerPadding ->
                // ColumnAndRows(modifier = Modifier.padding(innerPadding))
                // Scrolling(modifier = Modifier.padding(innerPadding))
                // FormularioValidacion(modifier = Modifier.padding(innerPadding))
                //CamaraCapture(modifier = Modifier.padding(innerPadding))
                /* ListaElementos(
                    elementos = List(100) { "Elemento #$it" },
                    modifier = Modifier.padding(innerPadding)
                )
                **/
                //NavegacionPantallas(modifier = Modifier.padding(innerPadding))
                /*
                CardConImagen(
                    imagen = painterResource(id = R.drawable.ic_launcher_foreground),
                    titulo = "Título de la tarjeta",
                    descripcion = "Descripción de la tarjeta",
                    modifier = Modifier.padding(innerPadding)
                )
                **/
                AnimacionesEjemplo(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}