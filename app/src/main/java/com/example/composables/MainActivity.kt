package com.example.composables

import LocationScreen
import TablaElementos
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
import com.example.composables.conocimiento.ModifiersExamples
import com.example.composables.conocimiento.StateExamples
import com.example.composables.ui.FormularioScreen


val datos = listOf(
    listOf("id","Nombre", "Edad", "Ciudad"), // Encabezados
    listOf("1","Ana", "25", "Madrid"),
    listOf("2","Luis", "30", "Barcelona"),
    listOf("3","Marta", "28", "Valencia"),
    listOf("3","Marta", "28", "Valencia"),
)
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
                //AnimacionesEjemplo(modifier = Modifier.padding(innerPadding))
                // TablaElementos(elementos = datos, modifier = Modifier.padding(innerPadding))
                // ModifiersExamples(modifier = Modifier.padding(innerPadding))
                //StateExamples(modifier = Modifier.padding(innerPadding))
                //FormularioScreen(modifier = Modifier.padding(innerPadding))
                LocationScreen(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}