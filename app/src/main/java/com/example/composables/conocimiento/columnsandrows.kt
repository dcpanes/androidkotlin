package com.example.composables.conocimiento

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ColumnAndRows(modifier: Modifier = Modifier) {

    Column {
        Row {
            Text("1")
            Text("2")
        }
        Row {
            Text("3")
            Text("4")
        }
    }
}