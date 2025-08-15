package pl.michal_cyran.math_tools.drawings.coordinate_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters

@Composable
fun CoordinateSystemParametersSection(
    onParametersChange: (DrawingParameters.CoordinateSystemParameters) -> Unit = {}
) {
    var minX by remember { mutableStateOf(-5) }
    var maxX by remember { mutableStateOf(5) }
    var minY by remember { mutableStateOf(-5) }
    var maxY by remember { mutableStateOf(5) }

    val textMeasurer = rememberTextMeasurer()

    LaunchedEffect(minX, maxY, minY, maxY) {
        onParametersChange(
            DrawingParameters.CoordinateSystemParameters(
                minX = minX,
                maxX = maxX,
                minY = minY,
                maxY = maxY,
                includeGrid = false,
                textMeasurer = textMeasurer
            )
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = minX.toString(),
            onValueChange = { newValue ->
                minX = newValue.toIntOrNull() ?: 0
            },
            label = { Text("Min x") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            singleLine = true
        )

        OutlinedTextField(
            value = maxX.toString(),
            onValueChange = { newValue ->
                maxX = newValue.toIntOrNull() ?: 0
            },
            label = { Text("Max x") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            singleLine = true
        )

        OutlinedTextField(
            value = minY.toString(),
            onValueChange = { newValue ->
                minY = newValue.toIntOrNull() ?: 0
            },
            label = { Text("Min y") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            singleLine = true
        )
        OutlinedTextField(
            value = maxY.toString(),
            onValueChange = { newValue ->
                maxY = newValue.toIntOrNull() ?: 0
            },
            label = { Text("Max y") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.weight(1f),
            singleLine = true
        )

    }
}