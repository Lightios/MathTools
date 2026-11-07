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
import pl.michal_cyran.math_tools.ui.NumericInput

@Composable
fun CoordinateSystemParametersSection(
    onParametersChange: (DrawingParameters.CoordinateSystemParameters) -> Unit = {}
) {
    var size by remember { mutableStateOf(5) }

    val textMeasurer = rememberTextMeasurer()

    LaunchedEffect(size) {
        onParametersChange(
            DrawingParameters.CoordinateSystemParameters(
                size = size,
                includeGrid = false,
                textMeasurer = textMeasurer
            )
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        NumericInput(
            value = size,
            onValueChange = {
                size = it
            },
            label = "Przedział osi",
            minValue = 5,
            maxValue = 20,
            modifier = Modifier.weight(1f),
        )
    }
}