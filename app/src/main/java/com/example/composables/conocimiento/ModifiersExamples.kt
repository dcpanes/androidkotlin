package com.example.composables.conocimiento

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ModifiersExamples(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Orden: padding then background vs background then padding")
        ModifierOrderExample()

        Text(text = "Scope padre vs scope hijo (padding del padre aplica a todo)")
        ParentPaddingScopeExample()

        Text(text = "Chaining / then() y pasar `modifier` a hijos")
        PassingModifierExample(modifier = Modifier.background(Color(0xFFEEEEFF)))

        Text(text = "Weight y alineamiento en Row")
        WeightAndAlignExample()
    }
}

@Composable
private fun ModifierOrderExample() {
    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        // padding antes de background -> el fondo respeta el padding interno (background dibuja solo el área restante)
        Box(
            modifier = Modifier
                .size(80.dp)
                .padding(8.dp)
                .background(Color(0xFFB3E5FC)),
            contentAlignment = Alignment.Center
        ) {
            Text("pad->bg", color = Color.Black)
        }

        // background antes de padding -> el fondo ocupa todo el size, el padding crea espacio interior sobre ese fondo
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color(0xFFFFCCBC))
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("bg->pad", color = Color.Black)
        }
    }
}

@Composable
private fun ParentPaddingScopeExample() {
    // El padding aplicado al padre separa a todos los hijos del borde; cada hijo puede añadir su propio padding
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF1F8E9))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFC8E6C9))
                .padding(8.dp)
                .height(40.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Hijo 1")
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFA5D6A7))
                .padding(8.dp)
                .height(40.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text("Hijo 2")
        }
    }
}

@Composable
private fun PassingModifierExample(modifier: Modifier) {
    // El `modifier` que llega puede combinarse usando then() o aplicándose antes/después
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
        // aplicar el modifier externo + más modificaciones
        Box(
            modifier = modifier
                .size(64.dp)
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("mod\nthen", color = Color.Black)
        }

        // aplicar modificaciones locales y luego añadir comportamiento clicable.
        // Nota: clickable después de padding hace que el área clicable sea más pequeña (solo el espacio interior)
        Box(
            modifier = Modifier
                .size(64.dp)
                .padding(6.dp)
                .clickable { /* acción */ }
                .background(Color(0xFFFFF59D)),
            contentAlignment = Alignment.Center
        ) {
            Text("pad\nclick", color = Color.Black)
        }

        // clickable primero, padding después -> el área clicable incluye también el padding (ripple puede abarcar más)
        Box(
            modifier = Modifier
                .size(64.dp)
                .clickable { /* acción */ }
                .padding(6.dp)
                .background(Color(0xFFCE93D8)),
            contentAlignment = Alignment.Center
        ) {
            Text("click\npad", color = Color.Black)
        }
    }
}

@Composable
private fun WeightAndAlignExample() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(48.dp)
                .background(Color(0xFFB2DFDB)),
            contentAlignment = Alignment.Center
        ) {
            Text("weight 1")
        }
        Box(
            modifier = Modifier
                .weight(2f)
                .height(48.dp)
                .background(Color(0xFFB3E5FC)),
            contentAlignment = Alignment.Center
        ) {
            Text("weight 2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewModifiersExamples() {
    Surface {
        ModifiersExamples()
    }
}
