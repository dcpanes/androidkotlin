import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TablaElementos(
    elementos: List<List<String>>,
    modifier: Modifier = Modifier
) {
    // Se crea una columna principal que contendrá todas las filas de la tabla
    Column(modifier = modifier) {
        // Se recorre cada fila de la lista de elementos
        elementos.forEach { fila ->
            // Por cada fila, se crea una Row (fila visual)
            Row {
                // Se recorre cada celda de la fila actual
                fila.forEach { celda ->
                    // Cada celda se muestra en un Box con borde y padding
                    Box(
                        modifier = Modifier
                            .weight(1f) // Ocupa el mismo espacio que las demás celdas
                            .border(1.dp, Color.Gray) // Borde de la celda
                            .padding(8.dp) // Espacio interno de la celda
                    ) {
                        Text(text = celda) // Texto de la celda
                    }
                }
            }
        }
    }
}
