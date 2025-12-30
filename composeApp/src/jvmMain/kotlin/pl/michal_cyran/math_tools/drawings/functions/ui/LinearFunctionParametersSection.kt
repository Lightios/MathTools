package pl.michal_cyran.math_tools.drawings.functions.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import pl.michal_cyran.math_tools.drawings.core.DrawingParameters
import pl.michal_cyran.math_tools.ui.NumericInput

@Composable
fun LinearFunctionParametersSection(
    onParametersChange: (DrawingParameters.FunctionParameters.LinearFunctionParameters) -> Unit,
) {
    var a by remember { mutableStateOf(-5f) }
    var b by remember { mutableStateOf(5f) }

    val textMeasurer = rememberTextMeasurer()
    var formula by remember { mutableStateOf("y = ${a}x + $b") }

    val coordinateSystemParameters by remember {
        mutableStateOf(
            DrawingParameters.CoordinateSystemParameters(
                minX = -10,
                maxX = 10,
                minY = -10,
                maxY = 10,
                includeGrid = true,
                textMeasurer = textMeasurer
            )
        )
    }


    LaunchedEffect(a, b) {
        onParametersChange(
            DrawingParameters.FunctionParameters.LinearFunctionParameters(
                a = a,
                b = b,
                csParameters = coordinateSystemParameters
            )
        )
        formula = "y = ${a}x + $b"
    }


    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        NumericInput(
            value = a,
            onValueChange = {
                a = it
            },
            label = "Współczynnik kierunkowy (a)",
            minValue = -10f,
            maxValue = 10f,
            modifier = Modifier.weight(1f),
        )

        NumericInput(
            value = b,
            onValueChange = {
                b = it
            },
            label = "Wyraz wolny (b)",
            minValue = -10f,
            maxValue = 10f,
            modifier = Modifier.weight(1f),
        )

        Text(
            text = formula,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}